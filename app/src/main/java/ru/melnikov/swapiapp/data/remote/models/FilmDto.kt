package ru.melnikov.swapiapp.data.remote.models

import com.google.gson.annotations.SerializedName

data class FilmDto(
    @SerializedName("title")
    val title: String?,
    @SerializedName("episode_id")
    val episodeId: Int,
    @SerializedName("characters")
    val characters: List<String>?,
    @SerializedName("opening_crawl")
    val openingCrawl: String?,
    @SerializedName("director")
    val director: String?,
    @SerializedName("producer")
    val producer: String?,
    @SerializedName("release_date")
    val releaseDate: String?
)
