package ru.melnikov.swapiapp.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.melnikov.swapiapp.domain.repository.PeopleRepository
import ru.melnikov.swapiapp.presentation.navigation.Routes
import ru.melnikov.swapiapp.presentation.state.PeopleSideEffects
import ru.melnikov.swapiapp.presentation.state.PeopleState
import ru.melnikov.swapiapp.utils.Resource
import ru.melnikov.swapiapp.utils.StringResourceProvider

class PeopleViewModel(
    private val peopleRepository: PeopleRepository,
    private val stringResourceProvider: StringResourceProvider,
    savedStateHandle: SavedStateHandle
) : ViewModel(), ContainerHost<PeopleState, PeopleSideEffects> {

    override val container = container<PeopleState, PeopleSideEffects>(PeopleState())

    init {
        intent {
            val args = savedStateHandle.toRoute<Routes.PeopleScreen>()
            reduce {
                state.copy(
                    filmId = args.filmId,
                    filmTitle = args.filmTitle,
                    peopleIds = args.peopleIds
                )
            }
        }
        getPeople()
    }

    fun getPeople(fetchFromRemote: Boolean = false) = intent {
        reduce { state.copy(isLoading = true) }
        peopleRepository.getFilmPersons(state.peopleIds, state.filmId, fetchFromRemote)
            .collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        reduce {
                            state.copy(
                                people = resource.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> {
                        reduce { state.copy(isLoading = false) }
                        postSideEffect(
                            PeopleSideEffects.ShowError(
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
        postSideEffect(PeopleSideEffects.NavigateBack)
    }

    fun navigateToPlanet(planetId: Int) = intent {
        postSideEffect(PeopleSideEffects.NavigateToPlanet(planetId))
    }

}