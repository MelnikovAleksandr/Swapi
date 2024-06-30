package ru.melnikov.swapiapp.data.remote.mappers

import androidx.core.net.toUri
import ru.melnikov.swapiapp.data.local.entity.PersonEntity
import ru.melnikov.swapiapp.data.remote.models.PersonDto

fun PersonDto.toPersonEntity() =
    PersonEntity(
        name = name,
        gender = gender ?: "",
        birthYear = birthYear ?: "",
        urlId = url.toUri()
            .lastPathSegment
            ?.toInt() ?: 0,
        homeWorld = homeWorld?.toUri()
            ?.lastPathSegment
            ?.toInt() ?: 0
    )