package ru.melnikov.swapiapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.melnikov.swapiapp.data.remote.models.FilmsResponse
import ru.melnikov.swapiapp.data.remote.models.PersonDto

interface SwapiApi {

    @GET("films/")
    suspend fun getFilm(): Response<FilmsResponse>

    @GET("people/{id}")
    suspend fun getPerson(@Path("id") id: Int): Response<PersonDto>

}