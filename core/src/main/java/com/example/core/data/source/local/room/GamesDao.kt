package com.example.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.core.data.source.local.entity.GameDetailEntity
import com.example.core.data.source.local.entity.GameEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface GamesDao {

    @Query("SELECT * FROM games")
    fun getAllGames(): Flowable<List<GameEntity>>

    @Query("SELECT * FROM games WHERE is_favorite = 1")
    fun getFavouriteGames(): Flowable<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavouriteGame(games: List<GameEntity>): Completable

    @Update
    fun updateFavouriteGame(game: GameEntity): Completable

    @Query("UPDATE games SET is_favorite =:isFavorite WHERE game_id =:gameId")
    fun updateGamesFavoriteStatusById(gameId: Int, isFavorite: Boolean): Completable

    @Query("SELECT * FROM games WHERE game_id=:gameId")
    fun getGameById(gameId: Int): Flowable<GameEntity>

    @Query("SELECT * FROM game_detail WHERE id = :gameDetailId")
    fun getGameDetailById(gameDetailId: Int): Flowable<GameDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGameDetail(gameDetailEntity: GameDetailEntity): Completable

    @Query("SELECT * FROM games where name LIKE :name")
    fun searchGameByName(name: String): Flowable<List<GameEntity>>
}