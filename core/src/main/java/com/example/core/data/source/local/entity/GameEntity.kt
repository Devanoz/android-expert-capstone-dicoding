package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "games")
data class GameEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("game_id")
    @NonNull
    var id: Int,
    @ColumnInfo("fk_game_detail")
    var fkGameDetail: Int?,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "metacritic_score")
    var metacriticScore: Int,
    @ColumnInfo("released")
    var released: String,
    @ColumnInfo("background_image_url")
    var backgroundImageUrl: String,
    @ColumnInfo("description")
    var descriptionRaw: String,
    @ColumnInfo("is_favorite")
    var isFavorite: Boolean,
)