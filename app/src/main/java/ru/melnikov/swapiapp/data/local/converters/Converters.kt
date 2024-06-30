package ru.melnikov.swapiapp.data.local.converters

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromIntList(list: List<Int>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toIntList(data: String): List<Int> {
        return data.split(",").map { it.toInt() }
    }
}