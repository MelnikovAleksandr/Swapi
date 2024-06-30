package ru.melnikov.swapiapp.utils

import ru.melnikov.swapiapp.R

enum class HttpError {
    Https429Error,
    Https400Errors,
    Https500Errors,
    TimeoutException,
    MissingConnection,
    NetworkError,
    UnknownError
}

enum class Gender(val stringRes: Int) {
    MALE(R.string.male_gender),
    FEMALE(R.string.female_gender),
    NON(R.string.non_gender)
}