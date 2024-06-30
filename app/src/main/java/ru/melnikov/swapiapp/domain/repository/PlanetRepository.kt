package ru.melnikov.swapiapp.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.melnikov.swapiapp.domain.models.Planet
import ru.melnikov.swapiapp.utils.Resource

interface PlanetRepository {

    suspend fun getPlanet(planetId: Int, fetchFromRemote: Boolean): Flow<Resource<Planet>>

}