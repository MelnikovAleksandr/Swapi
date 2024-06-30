package ru.melnikov.swapiapp.data.local.mappers

import ru.melnikov.swapiapp.data.local.entity.PersonEntity
import ru.melnikov.swapiapp.domain.models.Person
import ru.melnikov.swapiapp.utils.Gender

fun PersonEntity?.toPerson() =
    Person(
        id = this?.urlId ?: 0,
        name = this?.name ?: "",
        birthYear = this?.birthYear ?: "",
        gender =
        when (this?.gender) {
            "male" -> Gender.MALE
            "female" -> Gender.FEMALE
            else -> Gender.NON
        },
        homeWorld = this?.homeWorld ?: 0
    )