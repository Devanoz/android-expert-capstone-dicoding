package com.example.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_detail")
data class GameDetailEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "metacritic")
    var metacritic: Int,
    @ColumnInfo(name = "released")
    var released: String,
    @ColumnInfo(name = "background_image_url")
    var backgroundImage: String,
    @ColumnInfo(name = "description")
    var description: String,
)
