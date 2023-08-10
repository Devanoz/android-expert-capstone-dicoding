package com.example.core.data.source.local

import com.example.core.data.source.local.entity.GameDetailEntity
import com.example.core.data.source.local.entity.GameEntity
import com.example.core.data.source.local.room.GamesDao
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val gamesDao: GamesDao) {

    fun searchGameByName(name: String) = gamesDao.searchGameByName(name)

    fun getAllGames(): Flowable<List<GameEntity>> = gamesDao.getAllGames()

    fun getFavouriteGames(): Flowable<List<GameEntity>> = gamesDao.getFavouriteGames()

    fun insertGames(games: List<GameEntity>) = gamesDao.insertFavouriteGame(games)

    fun setFavourite(gameId: Int,isFavorite: Boolean) = gamesDao.updateGamesFavoriteStatusById(gameId,isFavorite)

    fun getGameById(gameId:Int) = gamesDao.getGameById(gameId)

    fun getGameDetailById(gameDetailId: Int) = gamesDao.getGameDetailById(gameDetailId)

    fun updateGames(game: GameEntity) =
        gamesDao.updateFavouriteGame(game)


    fun insertGameDetail(gameDetailEntity: GameDetailEntity) = gamesDao.insertGameDetail(gameDetailEntity)
}