package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Game
import com.example.core.domain.model.GameDetail
import com.example.core.domain.repository.IGamesRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GamesInteractor @Inject constructor (private val gameRepository: IGamesRepository):GameUseCase {
    override fun getAllGames(): Flowable<Resource<List<Game>>> {
        return gameRepository.getAllGames()
    }

    override fun getFavouriteGames(): Flowable<List<Game>> {
        return gameRepository.getAllFavouriteGames()
    }

    override fun setFavouriteGames(gameId: Int, isFavorite: Boolean) {
        gameRepository.setFavouriteGames(gameId,isFavorite)
    }

    override fun getGameById(gameId: Int): Flowable<Resource<GameDetail>> {
        return gameRepository.getGameById(gameId)
    }

    override fun searchGameByName(query: String): Flowable<List<Game>> {
        return gameRepository.searchGameByName(query)
    }
}