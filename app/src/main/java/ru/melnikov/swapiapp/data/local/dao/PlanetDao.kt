package ru.melnikov.swapiapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.melnikov.swapiapp.data.local.entity.PlanetEntity

@Dao
interface PlanetDao {

    @Query("SELECT * FROM planet WHERE urlId = :planetId")
    suspend fun getLocalPlanet(planetId: Int): PlanetEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanet(planet: PlanetEntity)

}