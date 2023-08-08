package com.example.core.data

import android.annotation.SuppressLint
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.local.entity.GameEntity
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.GameItem
import com.example.core.domain.model.Game
import com.example.core.domain.model.GameDetail
import com.example.core.domain.repository.IGamesRepository
import com.example.core.utils.AppExecutors
import com.example.core.utils.DataMapper
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGamesRepository {
    override fun getAllGames(): Flowable<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameItem>>() {
            override fun loadFromDb(): Flowable<List<Game>> {
                return localDataSource.getAllGames().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Game>?): Boolean {
                return data.isNullOrEmpty()
            }

            override fun createCall(): Flowable<ApiResponse<List<GameItem>>> {
                return remoteDataSource.getAllGames()
            }

            override fun saveCallResult(data: List<GameItem>) {
                val gameList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertGames(gameList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getAllFavouriteGames(): Flowable<List<Game>> {
        return localDataSource.getFavouriteGames().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavouriteGames(game: Game, isFavorite: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavourite(gameEntity, isFavorite) }
    }

    @SuppressLint("CheckResult")
    override fun getGameById(gameId: Int): Flowable<Resource<GameDetail>> {
        val result = PublishSubject.create<Resource<GameDetail>>()

        val disposable = localDataSource.getGameById(gameId)
            .subscribeOn(Schedulers.io())
            .subscribe({ gameEntity: GameEntity ->
                if(gameEntity.fkGameDetail == null) {
                    remoteDataSource.getGameDetailById(gameId).subscribeOn(Schedulers.io()).subscribe { gameDetailResponse ->
                        when(gameDetailResponse) {
                            ApiResponse.Empty -> {
                                result.onNext(Resource.Loading())
                            }
                            is ApiResponse.Error -> {
                                result.onNext(Resource.Error("value is empty"))
                            }
                            is ApiResponse.Success -> {
                                result.onNext(Resource.Success(DataMapper.mapResponseToGameDetailDomain(gameDetailResponse.data)))
                                gameEntity.fkGameDetail = gameDetailResponse.data.id
                                localDataSource.updateGames(gameEntity).subscribeOn(Schedulers.io()).subscribe()
                                localDataSource.insertGameDetail(DataMapper.mapResponseToGameDetailEntity(gameDetailResponse.data)).subscribeOn(Schedulers.io()).subscribe()
                            }
                        }
                    }
                }else {
                    localDataSource.getGameDetailById(gameId).subscribeOn(Schedulers.io())
                        .subscribe { gameDetailEntity ->
                            result.onNext(Resource.Success(DataMapper.mapGameDetailEntityToDomain(gameDetailEntity)))
                        }
                }
            }, {
                result.onNext(Resource.Error(it.message.toString()))
            })
        result.doOnComplete { disposable.dispose() }
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }
}