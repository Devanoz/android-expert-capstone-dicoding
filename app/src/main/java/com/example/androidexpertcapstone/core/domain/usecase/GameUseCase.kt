package com.example.androidexpertcapstone.core.domain.usecase

import com.example.androidexpertcapstone.core.data.Resource
import com.example.androidexpertcapstone.core.domain.model.Game
import com.example.androidexpertcapstone.core.domain.model.GameDetail
import io.reactivex.Flowable

interface GameUseCase {
    fun getAllGames(): Flowable<Resource<List<Game>>>
    fun getFavouriteGames(): Flowable<List<Game>>
    fun setFavouriteGames(game: Game,isFavorite: Boolean)
    fun getGameById(gameId: Int): Flowable<Resource<GameDetail>>
}