package ru.melnikov.swapiapp.domain.models

import java.time.LocalDate

data class Film(
    val id: Int,
    val title: String,
    val director: String,
    val producer: List<String>,
    val releaseDate: LocalDate?,
    val openingCrawl: String,
    val characters: List<Int>
)