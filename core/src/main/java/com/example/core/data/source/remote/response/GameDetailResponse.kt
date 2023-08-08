package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameDetailResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("metacritic")
    val metacritic: Int,
    @field:SerializedName("released")
    val released: String,
    @field:SerializedName("background_image")
    val backgroundImage: String,
    @field:SerializedName("description_raw")
    val description: String,
)