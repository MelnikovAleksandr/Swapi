package ru.melnikov.swapiapp.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.time.delay
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.melnikov.swapiapp.domain.repository.FilmRepository
import ru.melnikov.swapiapp.presentation.state.FilmsSideEffects
import ru.melnikov.swapiapp.presentation.state.FilmsState
import ru.melnikov.swapiapp.utils.Resource
import ru.melnikov.swapiapp.utils.StringResourceProvider
import java.time.Duration

class FilmsViewModel(
    private val filmRepository: FilmRepository,
    private val stringResourceProvider: StringResourceProvider
) : ViewModel(), ContainerHost<FilmsState, FilmsSideEffects> {

    override val container = container<FilmsState, FilmsSideEffects>(FilmsState())

    init {
        getFilms()
    }

    var searchTitle by mutableStateOf("")
        private set

    fun updateSearchTitle(input: String) {
        searchTitle = input
    }

    fun onClearTitleSearch() = intent {
        searchTitle = ""
    }

    fun getFilms(fetchFromRemote: Boolean = false) = intent {
        reduce { state.copy(isLoading = true) }
        filmRepository.getFilms(fetchFromRemote).collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    reduce { state.copy(films = resource.data ?: emptyList(), isLoading = false) }
                }

                is Resource.Error -> {
                    reduce { state.copy(isLoading = false) }
                    postSideEffect(
                        FilmsSideEffects.ShowError(
                            resource.httpErrors.toStringResText(
                                stringResourceProvider
                            )
                        )
                    )
                }
            }
        }
    }
}