package com.example.favourite

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.domain.model.Game
import com.example.core.domain.usecase.GameUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavouriteViewModel(private val gameUseCase: GameUseCase): ViewModel() {

    private val _favouriteGames = MutableLiveData<List<Game>>()
    val favouriteGames: LiveData<List<Game>> get() = _favouriteGames

    init {
        loadFavouriteGames()
    }

    @SuppressLint("CheckResult")
    private fun loadFavouriteGames() {
        gameUseCase.getFavouriteGames().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _favouriteGames.value = it
            }
    }

    fun setFavourite(gameId: Int, isFavorite: Boolean) {
        gameUseCase.setFavouriteGames(gameId,isFavorite)
    }
}