package ru.melnikov.swapiapp.data.remote.models

import com.google.gson.annotations.SerializedName

data class FilmsResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val nextPageUrl: String?,
    @SerializedName("previous")
    val previousPageUrl: String?,
    @SerializedName("results")
    val results: List<FilmDto>
)
