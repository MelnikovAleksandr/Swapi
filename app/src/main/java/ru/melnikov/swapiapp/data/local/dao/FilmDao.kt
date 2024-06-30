package ru.melnikov.swapiapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.melnikov.swapiapp.data.local.entity.FilmEntity

@Dao
interface FilmDao {

    @Query("SELECT * FROM films ORDER BY id ASC")
    fun getLocalFilms(): List<FilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilms(films: List<FilmEntity>): List<Long>
}