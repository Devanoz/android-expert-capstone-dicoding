package com.example.core.utils

import com.example.core.data.source.local.entity.GameDetailEntity
import com.example.core.data.source.local.entity.GameEntity
import com.example.core.data.source.remote.response.GameDetailResponse
import com.example.core.data.source.remote.response.GameItem
import com.example.core.domain.model.Game
import com.example.core.domain.model.GameDetail

object DataMapper {
    fun mapResponseToEntities(input: List<GameItem>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                id = it.id,
                name = it.name,
                fkGameDetail = null,
                metacriticScore = it.metacritic,
                released = it.released,
                backgroundImageUrl = it.backgroundImage,
                descriptionRaw = "",
                isFavorite = false
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>) =
        input.map {
            Game(
                id = it.id,
                name = it.name,
                metacriticScore = it.metacriticScore,
                released = it.released,
                backgroundImageUrl = it.backgroundImageUrl,
                descriptionRaw = it.backgroundImageUrl,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(game: Game) = GameEntity(
        id = game.id,
        name = game.name,
        fkGameDetail = null,
        metacriticScore = game.metacriticScore,
        released = game.released,
        backgroundImageUrl = game.backgroundImageUrl,
        descriptionRaw = "",
        isFavorite = false
    )


    fun mapResponseToGameDetailDomain(input: GameDetailResponse): GameDetail =
        GameDetail(
            id = input.id,
            name = input.name,
            metacritic = input.metacritic,
            released = input.released,
            backgroundImage = input.backgroundImage,
            description = input.backgroundImage
        )

    fun mapGameDetailEntityToDomain(input: GameDetailEntity): GameDetail =
        GameDetail(
            id = input.id,
            name = input.name,
            metacritic = input.metacritic,
            released = input.released,
            backgroundImage = input.backgroundImage,
            description = input.backgroundImage
        )

    fun mapResponseToGameDetailEntity(input: GameDetailResponse): GameDetailEntity =
        GameDetailEntity(
            id = input.id,
            name = input.name,
            metacritic = input.metacritic,
            released = input.released,
            backgroundImage = input.backgroundImage,
            description = input.description
        )

}