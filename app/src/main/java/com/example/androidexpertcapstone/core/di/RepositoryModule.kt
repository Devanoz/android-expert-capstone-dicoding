package com.example.androidexpertcapstone.core.di

import com.example.androidexpertcapstone.core.data.GameRepository
import com.example.androidexpertcapstone.core.domain.repository.IGamesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent


@Module(includes = [NetworkModule::class,DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideGamesRepository(gamesRepository: GameRepository): IGamesRepository
}