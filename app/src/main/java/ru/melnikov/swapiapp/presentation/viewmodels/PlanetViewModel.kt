package ru.melnikov.swapiapp.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.melnikov.swapiapp.domain.repository.PlanetRepository
import ru.melnikov.swapiapp.presentation.navigation.Routes
import ru.melnikov.swapiapp.presentation.state.PlanetSideEffects
import ru.melnikov.swapiapp.presentation.state.PlanetState
import ru.melnikov.swapiapp.utils.Resource
import ru.melnikov.swapiapp.utils.StringResourceProvider

class PlanetViewModel(
    private val planetRepository: PlanetRepository,
    private val stringResourceProvider: StringResourceProvider,
    savedStateHandle: SavedStateHandle
) : ViewModel(), ContainerHost<PlanetState, PlanetSideEffects> {

    override val container = container<PlanetState, PlanetSideEffects>(PlanetState())

    init {
        intent {
            val args = savedStateHandle.toRoute<Routes.PlanetsScreen>()
            reduce { state.copy(planetId = args.planetId) }
        }
        getPlanet()
    }

    fun getPlanet(fetchFromRemote: Boolean = false) = intent {
        reduce { state.copy(isLoading = true) }
        planetRepository.getPlanet(planetId = state.planetId, fetchFromRemote = fetchFromRemote)
            .collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        reduce { state.copy(planet = resource.data, isLoading = false) }
                    }

                    is Resource.Error -> {
                        reduce { state.copy(isLoading = false) }
                        postSideEffect(
                            PlanetSideEffects.ShowError(
                                resource.httpErrors.toStringResText(
                                    stringResourceProvider
                                )
                            )
                        )
                    }
                }
            }
    }


    fun navigateBack() = intent {
        postSideEffect(PlanetSideEffects.NavigateBack)
    }

}