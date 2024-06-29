package ru.melnikov.swapiapp.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object FilmsScreen : Routes()

    @Serializable
    data class PeopleScreen(
        val personId: Int,
        val peopleIds: List<Int>
    ) : Routes()

    @Serializable
    data class PlanetsScreen(
        val planetId: Int
    ) : Routes()
}