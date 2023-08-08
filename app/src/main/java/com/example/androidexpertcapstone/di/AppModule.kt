package com.example.androidexpertcapstone.di

import com.example.androidexpertcapstone.core.domain.usecase.GameUseCase
import com.example.androidexpertcapstone.core.domain.usecase.GamesInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideGameUsecase(gamesInteractor: GamesInteractor): GameUseCase
}