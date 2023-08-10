package com.example.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.domain.usecase.GameUseCase
import javax.inject.Inject

class FavouriteViewModelFactory @Inject constructor(private val gameUseCase: GameUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavouriteViewModel::class.java) -> {
                FavouriteViewModel(gameUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}