package com.example.androidexpertcapstone.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidexpertcapstone.core.data.source.local.entity.GameDetailEntity
import com.example.androidexpertcapstone.core.data.source.local.entity.GameEntity


@Database(version = 1, entities = [GameEntity::class,GameDetailEntity::class], exportSchema = false)
abstract class GamesDatabase: RoomDatabase() {
    abstract fun gamesDao(): GamesDao
}