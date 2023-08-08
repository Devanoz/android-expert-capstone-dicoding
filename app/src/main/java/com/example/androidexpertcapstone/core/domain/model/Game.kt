package com.example.androidexpertcapstone.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: Int,
    val name: String,
    val metacriticScore: Int,
    val released: String,
    val backgroundImageUrl: String,
    val descriptionRaw: String,
    val isFavorite: Boolean,
):Parcelable
