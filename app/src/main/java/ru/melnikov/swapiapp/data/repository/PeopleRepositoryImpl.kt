package ru.melnikov.swapiapp.data.repository

import androidx.core.net.toUri
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.melnikov.swapiapp.data.local.SwapiDatabase
import ru.melnikov.swapiapp.data.local.entity.FilmPersonCrossRef
import ru.melnikov.swapiapp.data.local.entity.PersonEntity
import ru.melnikov.swapiapp.data.local.mappers.toPerson
import ru.melnikov.swapiapp.data.remote.SwapiApi
import ru.melnikov.swapiapp.data.remote.mappers.toPersonEntity
import ru.melnikov.swapiapp.domain.models.Person
import ru.melnikov.swapiapp.domain.repository.PeopleRepository
import ru.melnikov.swapiapp.utils.Resource

class PeopleRepositoryImpl(
    private val api: SwapiApi,
    private val db: SwapiDatabase
) : PeopleRepository {

    private val personDao = db.personDao()

    override suspend fun getFilmPersons(
        ids: List<Int>,
        filmId: Int,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Person>>> {
        return flow {
            val localFilmPeople = personDao.getPeopleOfFilm(filmId)

            if (localFilmPeople.people.isEmpty() || fetchFromRemote) {
                val remotePeople = try {
                    coroutineScope {
                        ids
                            .map { async { api.getPerson(it) } }
                            .awaitAll()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    emit(Resource.Error(httpErrors = e.mapExceptionToHttpError()))
                    null
                }
                remotePeople?.let { responses ->
                    val peopleToInsert =
                        responses.map {
                            it.body()?.toPersonEntity() ?: PersonEntity()
                        }
                    responses.forEach { response ->
                        personDao.insertFilmPersonCrossRef(
                            FilmPersonCrossRef(
                                id = filmId,
                                urlId = response.body()?.url?.toUri()
                                    ?.lastPathSegment
                                    ?.toInt() ?: 0
                            )
                        )
                    }
                    personDao.insertPeople(peopleToInsert)
                    emit(Resource.Success(data = peopleToInsert.map { it.toPerson() }))
                }

            } else {
                emit(Resource.Success(data = localFilmPeople.people.map { it.toPerson() }))
            }
        }
    }
}