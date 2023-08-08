package com.example.core.di

import com.example.core.data.GameRepository
import com.example.core.domain.repository.IGamesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(includes = [NetworkModule::class,DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideGamesRepository(gamesRepository: GameRepository): IGamesRepository
}