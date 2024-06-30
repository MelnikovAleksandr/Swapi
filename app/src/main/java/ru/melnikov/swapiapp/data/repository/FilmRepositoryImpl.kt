package ru.melnikov.swapiapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.melnikov.swapiapp.data.local.SwapiDatabase
import ru.melnikov.swapiapp.data.local.mappers.toFilm
import ru.melnikov.swapiapp.data.remote.SwapiApi
import ru.melnikov.swapiapp.data.remote.mappers.toFilmEntity
import ru.melnikov.swapiapp.domain.models.Film
import ru.melnikov.swapiapp.domain.repository.FilmRepository
import ru.melnikov.swapiapp.utils.Resource

class FilmRepositoryImpl(
    private val api: SwapiApi,
    private val db: SwapiDatabase
) : FilmRepository {

    private val filmsDao = db.filmDao()

    override suspend fun getFilms(fetchFromRemote: Boolean): Flow<Resource<List<Film>>> {
        return flow {
            val localFilms = filmsDao.getLocalFilms()

            if (localFilms.isEmpty() || fetchFromRemote) {
                val remoteFilms = try {
                    api.getFilm()
                } catch (e: Exception) {
                    e.printStackTrace()
                    emit(Resource.Error(httpErrors = e.mapExceptionToHttpError()))
                    null
                }

                remoteFilms?.let { response ->
                    val filmsToInsert =
                        response.body()?.results?.map { it.toFilmEntity() } ?: emptyList()
                    filmsDao.insertFilms(filmsToInsert)
                    emit(Resource.Success(data = filmsToInsert.map { it.toFilm() }
                        .sortedBy { it.id }))
                }
            } else {
                emit(Resource.Success(data = localFilms.map { it.toFilm() }))
            }
        }
    }
}