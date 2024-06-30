package ru.melnikov.swapiapp.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.melnikov.swapiapp.domain.models.Film
import ru.melnikov.swapiapp.utils.Resource

interface FilmRepository {

    suspend fun getFilms(fetchFromRemote: Boolean): Flow<Resource<List<Film>>>

}