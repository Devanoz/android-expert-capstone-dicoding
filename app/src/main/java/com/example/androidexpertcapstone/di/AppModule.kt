package com.example.androidexpertcapstone.di

import com.example.core.domain.usecase.GameUseCase
import com.example.core.domain.usecase.GamesInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideGameUsecase(gamesInteractor: GamesInteractor): GameUseCase
}