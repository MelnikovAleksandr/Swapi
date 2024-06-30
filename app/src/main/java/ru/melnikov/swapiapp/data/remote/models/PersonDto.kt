package ru.melnikov.swapiapp.data.remote.models

import com.google.gson.annotations.SerializedName

data class PersonDto(
    @SerializedName("url")
    val url: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("birth_year")
    val birthYear: String?,
    @SerializedName("homeworld")
    val homeWorld: String?
)