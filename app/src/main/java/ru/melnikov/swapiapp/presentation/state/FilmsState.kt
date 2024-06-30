package ru.melnikov.swapiapp.presentation.state

import ru.melnikov.swapiapp.domain.models.Film

data class FilmsState(
    val isLoading: Boolean = true,
    val films: List<Film> = emptyList()
)

sealed class FilmsSideEffects {
    data class NavigateToPersons(val peopleIds: List<Int>, val filmTitle: String, val filmId: Int) :
        FilmsSideEffects()

    data class ShowError(val text: String) : FilmsSideEffects()
}
