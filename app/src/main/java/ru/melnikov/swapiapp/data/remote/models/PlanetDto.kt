package ru.melnikov.swapiapp.data.remote.models

import com.google.gson.annotations.SerializedName

data class PlanetDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("diameter")
    val diameter: Int?,
    @SerializedName("climate")
    val climate: String?,
    @SerializedName("gravity")
    val gravity: String?,
    @SerializedName("terrain")
    val terrain: String?,
    @SerializedName("population")
    val population: String?,
    @SerializedName("url")
    val url: String
)
