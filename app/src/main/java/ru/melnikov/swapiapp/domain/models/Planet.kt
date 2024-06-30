package ru.melnikov.swapiapp.domain.models

data class Planet(
    val urlId: Int,
    val name: String,
    val diameter: Int,
    val climate: List<String>,
    val gravity: String,
    val terrain: List<String>,
    val population: String
)
