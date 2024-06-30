package ru.melnikov.swapiapp.data.remote.mappers

import androidx.core.net.toUri
import ru.melnikov.swapiapp.data.local.entity.PlanetEntity
import ru.melnikov.swapiapp.data.remote.models.PlanetDto

fun PlanetDto.toPlanetEntity() =
    PlanetEntity(
        name = name ?: "",
        diameter = diameter ?: 0,
        climate = climate ?: "",
        gravity = gravity ?: "",
        terrain = terrain ?: "",
        population = population ?: "",
        urlId = url.toUri()
            .lastPathSegment
            ?.toInt() ?: 0
    )