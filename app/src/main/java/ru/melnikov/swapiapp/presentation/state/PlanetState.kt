package ru.melnikov.swapiapp.presentation.state

import ru.melnikov.swapiapp.domain.models.Planet

data class PlanetState(
    val isLoading: Boolean = true,
    val planet: Planet? = null,
    val planetId: Int = 0
)

sealed class PlanetSideEffects {
    data object NavigateBack : PlanetSideEffects()
    data class ShowError(val text: String) : PlanetSideEffects()
}
