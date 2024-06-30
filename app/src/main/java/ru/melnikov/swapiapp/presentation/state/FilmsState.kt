package ru.melnikov.swapiapp.presentation.state

import ru.melnikov.swapiapp.domain.models.Film

data class FilmsState(
    val isLoading: Boolean = false,
    val films: List<Film> = emptyList()
)

sealed class FilmsSideEffects {
    data object NavigateToPersons : FilmsSideEffects()
    data class ShowError(val text: String) : FilmsSideEffects()
}
