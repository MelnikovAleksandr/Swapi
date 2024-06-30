package ru.melnikov.swapiapp.data.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class FilmWithPeople(
    @Embedded val film: FilmEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "urlId",
        associateBy = Junction(FilmPersonCrossRef::class)
    )
    val people: List<PersonEntity>
)