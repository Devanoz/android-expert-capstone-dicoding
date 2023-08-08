package com.example.androidexpertcapstone.core.domain.usecase

import com.example.androidexpertcapstone.core.data.Resource
import com.example.androidexpertcapstone.core.domain.model.Game
import com.example.androidexpertcapstone.core.domain.model.GameDetail
import com.example.androidexpertcapstone.core.domain.repository.IGamesRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GamesInteractor @Inject constructor (private val gameRepository: IGamesRepository):GameUseCase {
    override fun getAllGames(): Flowable<Resource<List<Game>>> {
        return gameRepository.getAllGames()
    }

    override fun getFavouriteGames(): Flowable<List<Game>> {
        return gameRepository.getAllFavouriteGames()
    }

    override fun setFavouriteGames(game: Game, isFavorite: Boolean) {
        gameRepository.setFavouriteGames(game,isFavorite)
    }

    override fun getGameById(gameId: Int): Flowable<Resource<GameDetail>> {
        return gameRepository.getGameById(gameId)
    }
}