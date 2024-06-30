package ru.melnikov.swapiapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "people"
)
data class PersonEntity(
    @PrimaryKey
    val urlId: Int = 0,
    val name: String = "",
    val gender: String = "",
    val birthYear: String = ""
)