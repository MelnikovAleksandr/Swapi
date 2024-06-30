package ru.melnikov.swapiapp.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object FilmsScreen : Routes()

    @Serializable
    data class PeopleScreen(
        val peopleIds: List<Int>,
        val filmTitle: String,
        val filmId: Int
    ) : Routes()

    @Serializable
    data class PlanetsScreen(
        val planetId: Int
    ) : Routes()
}