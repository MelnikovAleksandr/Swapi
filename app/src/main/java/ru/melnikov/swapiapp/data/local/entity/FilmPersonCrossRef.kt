package ru.melnikov.swapiapp.data.local.entity

import androidx.room.Entity

@Entity(primaryKeys = ["id", "urlId"])
data class FilmPersonCrossRef(
    val id: Int,
    val urlId: Int
)

