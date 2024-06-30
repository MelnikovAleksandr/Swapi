package ru.melnikov.swapiapp.data.repository

import retrofit2.HttpException
import ru.melnikov.swapiapp.utils.HttpError
import java.io.IOException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException

fun Exception.mapExceptionToHttpError(): HttpError {
    return when (this) {
        is SocketTimeoutException -> HttpError.TimeoutException
        is ConnectException, is SocketException -> HttpError.MissingConnection
        is IOException -> HttpError.NetworkError
        is HttpException -> {
            when (this.code()) {
                429 -> HttpError.Https429Error
                in 400..499 -> HttpError.Https400Errors
                in 500..599 -> HttpError.Https500Errors
                else -> HttpError.UnknownError
            }
        }
        else -> HttpError.UnknownError
    }
}