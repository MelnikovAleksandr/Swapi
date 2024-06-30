package ru.melnikov.swapiapp.data.remote.mappers

import androidx.core.net.toUri
import ru.melnikov.swapiapp.data.local.entity.FilmEntity
import ru.melnikov.swapiapp.data.remote.models.FilmDto

fun FilmDto.toFilmEntity() =
    FilmEntity(
        id = episodeId,
        title = title ?: "",
        director = director ?: "",
        producer = producer ?: "",
        releaseDate = releaseDate ?: "",
        openingCrawl = openingCrawl ?: "",
        characters = characters?.map {
            it.toUri()
                .lastPathSegment
                ?.toInt() ?: 0
        } ?: emptyList()
    )