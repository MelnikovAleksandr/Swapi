package ru.melnikov.swapiapp.data.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.melnikov.swapiapp.data.local.SwapiDatabase
import ru.melnikov.swapiapp.data.remote.SwapiApi
import ru.melnikov.swapiapp.data.repository.FilmRepositoryImpl
import ru.melnikov.swapiapp.data.repository.PeopleRepositoryImpl
import ru.melnikov.swapiapp.data.repository.PlanetRepositoryImpl
import ru.melnikov.swapiapp.domain.repository.FilmRepository
import ru.melnikov.swapiapp.domain.repository.PeopleRepository
import ru.melnikov.swapiapp.domain.repository.PlanetRepository

private const val BASE_URL = "https://swapi.dev/api/"

val dataModule = module {

    single { okHttp() }

    single { gsonConverterFactory() }

    single { retrofit(get(), get()) }

    single { get<Retrofit>().create(SwapiApi::class.java) }

    single { SwapiDatabase.createDatabase(context = get()) }

    single<FilmRepository> { FilmRepositoryImpl(api = get(), db = get()) }

    single<PeopleRepository> { PeopleRepositoryImpl(api = get(), db = get()) }

    single<PlanetRepository> { PlanetRepositoryImpl(api = get(), db = get()) }

}

private fun gsonConverterFactory(): GsonConverterFactory =
    GsonConverterFactory.create()

private fun okHttp(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor())
        .build()

private fun retrofit(
    gsonConverterFactory: GsonConverterFactory,
    okHttpClient: OkHttpClient,
) = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(gsonConverterFactory)
    .client(okHttpClient)
    .build()

private fun loggingInterceptor() =
    HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BASIC)