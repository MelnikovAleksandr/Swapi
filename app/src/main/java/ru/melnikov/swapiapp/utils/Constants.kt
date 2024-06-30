package ru.melnikov.swapiapp.utils

enum class HttpError {
    Https429Error,
    Https400Errors,
    Https500Errors,
    TimeoutException,
    MissingConnection,
    NetworkError,
    UnknownError
}