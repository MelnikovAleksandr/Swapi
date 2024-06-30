package ru.melnikov.swapiapp.domain.models

import ru.melnikov.swapiapp.utils.Gender

data class Person(
    val id: Int,
    val name: String,
    val gender: Gender,
    val birthYear: String
)