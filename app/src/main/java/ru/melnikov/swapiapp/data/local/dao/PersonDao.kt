package ru.melnikov.swapiapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.melnikov.swapiapp.data.local.entity.FilmPersonCrossRef
import ru.melnikov.swapiapp.data.local.entity.FilmWithPeople
import ru.melnikov.swapiapp.data.local.entity.PersonEntity

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(people: List<PersonEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilmPersonCrossRef(crossRef: FilmPersonCrossRef)

    @Transaction
    @Query("SELECT * FROM films WHERE id = :id")
    suspend fun getPeopleOfFilm(id: Int): FilmWithPeople

}