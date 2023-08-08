package com.example.androidexpertcapstone.core.data.source.remote.network

import com.example.androidexpertcapstone.core.data.source.remote.response.GameDetailResponse
import com.example.androidexpertcapstone.core.data.source.remote.response.GamesResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("games?key=fefc2e4e14e5462987cb8acea4b85734")
    fun getAllGames(): Flowable<GamesResponse>

    @GET("games/{gameId}?key=fefc2e4e14e5462987cb8acea4b85734")
    fun getGameById(@Path("gameId") gameId: Int): Flowable<GameDetailResponse>
}