package ru.melnikov.swapiapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.melnikov.swapiapp.data.local.converters.Converters

@Entity(
    tableName = "films"
)
@TypeConverters(Converters::class)
data class FilmEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val openingCrawl: String,
    val characters: List<Int>
)

