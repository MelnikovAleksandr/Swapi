package ru.melnikov.swapiapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.melnikov.swapiapp.data.local.converters.Converters
import ru.melnikov.swapiapp.data.local.dao.FilmDao
import ru.melnikov.swapiapp.data.local.entity.FilmEntity

@Database(
    entities = [
        FilmEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class SwapiDatabase : RoomDatabase() {

    abstract fun filmDao(): FilmDao

    companion object {
        private const val DATABASE_NAME = "swapi_database"
        fun createDatabase(context: Context) =
            Room
                .databaseBuilder(
                    context,
                    SwapiDatabase::class.java,
                    DATABASE_NAME
                )
                .build()
    }
}