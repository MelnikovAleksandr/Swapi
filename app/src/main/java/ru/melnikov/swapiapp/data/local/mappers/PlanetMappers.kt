package ru.melnikov.swapiapp.data.local.mappers

import ru.melnikov.swapiapp.data.local.entity.PlanetEntity
import ru.melnikov.swapiapp.domain.models.Planet

fun PlanetEntity.toPlanet() =
    Planet(
        name = name,
        diameter = diameter,
        population = population,
        urlId = urlId,
        gravity = gravity,
        climate = climate.split(","),
        terrain = terrain.split(",")
    )