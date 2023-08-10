package com.example.androidexpertcapstone

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.data.Resource
import com.example.core.domain.model.Game
import com.example.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (private val gamesUseCase: GameUseCase) : ViewModel() {
    private val _games = MutableLiveData<Resource<List<Game>>>()
    val games: LiveData<Resource<List<Game>>> get() = _games

    private val compositeDisposable = CompositeDisposable()

    init {
        loadAllGames()
    }

    @SuppressLint("CheckResult")
    fun  searchGameByName(query: String) {
        gamesUseCase.searchGameByName(query).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _games.value = Resource.Success(it)
            }
    }

    @SuppressLint("CheckResult")
    private fun loadAllGames() {
        gamesUseCase.getAllGames().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .startWith(Resource.Loading())
            .subscribe {
                _games.value = it
            }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}