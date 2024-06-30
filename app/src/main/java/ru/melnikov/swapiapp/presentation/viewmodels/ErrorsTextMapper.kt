package ru.melnikov.swapiapp.presentation.viewmodels

import ru.melnikov.swapiapp.R
import ru.melnikov.swapiapp.utils.HttpError
import ru.melnikov.swapiapp.utils.StringResourceProvider

fun HttpError?.toStringResText(stringResourceProvider: StringResourceProvider): String {
    return when (this) {
        HttpError.Https429Error -> stringResourceProvider.getString(R.string.http_429_error)
        HttpError.Https400Errors -> stringResourceProvider.getString(R.string.http_400_errors)
        HttpError.Https500Errors -> stringResourceProvider.getString(R.string.http_500_errors)
        HttpError.TimeoutException -> stringResourceProvider.getString(R.string.http_timeout_error)
        HttpError.MissingConnection -> stringResourceProvider.getString(R.string.http_missing_error)
        HttpError.NetworkError -> stringResourceProvider.getString(R.string.http_network_error)
        HttpError.UnknownError -> stringResourceProvider.getString(R.string.http_unknown_error)
        else -> stringResourceProvider.getString(R.string.http_unknown_error)
    }
}