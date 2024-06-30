package ru.melnikov.swapiapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.melnikov.swapiapp.data.local.SwapiDatabase
import ru.melnikov.swapiapp.data.local.mappers.toPlanet
import ru.melnikov.swapiapp.data.remote.SwapiApi
import ru.melnikov.swapiapp.data.remote.mappers.toPlanetEntity
import ru.melnikov.swapiapp.domain.models.Planet
import ru.melnikov.swapiapp.domain.repository.PlanetRepository
import ru.melnikov.swapiapp.utils.Resource

class PlanetRepositoryImpl(
    private val api: SwapiApi,
    private val db: SwapiDatabase
) : PlanetRepository {

    private val planetDao = db.planetDao()

    override suspend fun getPlanet(
        planetId: Int,
        fetchFromRemote: Boolean
    ): Flow<Resource<Planet>> {
        return flow {
            val localPlanet = planetDao.getLocalPlanet(planetId)

            if (localPlanet == null || fetchFromRemote) {
                val remotePlanet = try {
                    api.getPlanet(planetId)
                } catch (e: Exception) {
                    e.printStackTrace()
                    emit(Resource.Error(httpErrors = e.mapExceptionToHttpError()))
                    null
                }

                remotePlanet?.let { response ->
                    val planetToInsert =
                        response.body()?.toPlanetEntity()
                    if (planetToInsert != null) {
                        planetDao.insertPlanet(planetToInsert)
                        emit(Resource.Success(data = planetToInsert.toPlanet()))
                    }
                }
            } else {
                emit(Resource.Success(data = localPlanet.toPlanet()))
            }
        }
    }
}