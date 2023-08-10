package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Game
import com.example.core.domain.model.GameDetail
import io.reactivex.Flowable

interface IGamesRepository {

    fun getAllGames(): Flowable<Resource<List<Game>>>

    fun getAllFavouriteGames(): Flowable<List<Game>>

    fun setFavouriteGames(gameId: Int, isFavorite: Boolean)

    fun getGameById(gameId: Int): Flowable<Resource<GameDetail>>

    fun searchGameByName(query: String): Flowable<List<Game>>
}