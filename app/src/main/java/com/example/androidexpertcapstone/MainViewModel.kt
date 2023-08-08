package com.example.androidexpertcapstone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.example.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (private val gamesUseCase: GameUseCase) : ViewModel() {
    val games = gamesUseCase.getAllGames().toLiveData()
}