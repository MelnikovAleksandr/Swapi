package ru.melnikov.swapiapp.data.local.mappers

import ru.melnikov.swapiapp.data.local.entity.FilmEntity
import ru.melnikov.swapiapp.domain.models.Film
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun FilmEntity.toFilm() =
    Film(
        id = id,
        title = title,
        director = director,
        producer = producer.split(","),
        releaseDate = releaseDate.toLocalDate(),
        openingCrawl = openingCrawl,
        characters = characters
    )

fun String.toLocalDate(): LocalDate? {
    return try {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        LocalDate.parse(this, formatter)
    } catch (e: Exception) {
        null
    }
}