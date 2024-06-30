package ru.melnikov.swapiapp.utils

sealed class Resource<T>(
    val data: T? = null,
    val httpErrors: HttpError? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(
        data: T? = null,
        httpErrors: HttpError? = null
    ) :
        Resource<T>(data, httpErrors)
}
