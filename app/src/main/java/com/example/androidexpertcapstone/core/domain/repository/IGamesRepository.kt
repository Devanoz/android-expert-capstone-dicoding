package com.example.androidexpertcapstone.core.domain.repository

import com.example.androidexpertcapstone.core.data.Resource
import com.example.androidexpertcapstone.core.data.source.local.entity.GameEntity
import com.example.androidexpertcapstone.core.data.source.remote.network.ApiResponse
import com.example.androidexpertcapstone.core.domain.model.Game
import com.example.androidexpertcapstone.core.domain.model.GameDetail
import io.reactivex.Flowable

interface IGamesRepository {

    fun getAllGames(): Flowable<Resource<List<Game>>>

    fun getAllFavouriteGames(): Flowable<List<Game>>

    fun setFavouriteGames(game: Game, isFavorite: Boolean)

    fun getGameById(gameId: Int): Flowable<Resource<GameDetail>>
}