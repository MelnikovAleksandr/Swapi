package ru.melnikov.swapiapp.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.melnikov.swapiapp.domain.models.Person
import ru.melnikov.swapiapp.utils.Resource

interface PeopleRepository {

    suspend fun getFilmPersons(
        ids: List<Int>,
        filmId: Int,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Person>>>

}