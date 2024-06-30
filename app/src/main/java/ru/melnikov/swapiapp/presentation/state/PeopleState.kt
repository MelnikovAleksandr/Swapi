package ru.melnikov.swapiapp.presentation.state

import ru.melnikov.swapiapp.domain.models.Person

data class PeopleState(
    val isLoading: Boolean = true,
    val people: List<Person> = emptyList(),
    val peopleIds: List<Int> = emptyList(),
    val filmId: Int = 0,
    val filmTitle: String = ""
)

sealed class PeopleSideEffects {
    data object NavigateToPlanet : PeopleSideEffects()
    data object NavigateBack : PeopleSideEffects()
    data class ShowError(val text: String) : PeopleSideEffects()
}