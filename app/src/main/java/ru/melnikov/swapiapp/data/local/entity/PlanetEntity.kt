package ru.melnikov.swapiapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "planet"
)
data class PlanetEntity(
    @PrimaryKey
    val urlId: Int,
    val name: String,
    val diameter: Int,
    val climate: String,
    val gravity: String,
    val terrain: String,
    val population: String
)
