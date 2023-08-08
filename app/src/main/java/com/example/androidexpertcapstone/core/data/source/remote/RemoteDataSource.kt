package com.example.androidexpertcapstone.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.example.androidexpertcapstone.core.data.source.remote.network.ApiResponse
import com.example.androidexpertcapstone.core.data.source.remote.network.ApiService
import com.example.androidexpertcapstone.core.data.source.remote.response.GameDetailResponse
import com.example.androidexpertcapstone.core.data.source.remote.response.GameItem
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    @SuppressLint("CheckResult")
    fun getAllGames(): Flowable<ApiResponse<List<GameItem>>> {
        val resultData = PublishSubject.create<ApiResponse<List<GameItem>>>()

        val client = apiService.getAllGames()

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                val dataArray = it.results
                resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, {
                resultData.onNext(ApiResponse.Error(it.message.toString()))
                Log.e("RemoteDataSource", it.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getGameDetailById(gameId: Int): Flowable<ApiResponse<GameDetailResponse>> {
        val resultData = PublishSubject.create<ApiResponse<GameDetailResponse>>()

        val client = apiService.getGameById(gameId)

        client
            .subscribeOn(Schedulers.computation())
            .take(1)
            .subscribe({
                val game = it
                resultData.onNext(if(game!=null) ApiResponse.Success(game) else ApiResponse.Empty)
            }, {
                resultData.onNext(ApiResponse.Error(it.message.toString()))
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}