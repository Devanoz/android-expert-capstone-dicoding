package com.example.androidexpertcapstone.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.example.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(private val gameUseCase: GameUseCase): ViewModel() {
//    fun getGameById(gameId: Int) = gameUseCase.getGameById(gameId).toLiveData()
}