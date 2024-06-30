package ru.melnikov.swapiapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import ru.melnikov.swapiapp.data.remote.models.FilmsResponse

interface SwapiApi {

    @GET("films/")
    suspend fun getFilm(): Response<FilmsResponse>

}