package ru.melnikov.swapiapp.presentation.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.plusmobileapps.konnectivity.Konnectivity
import com.plusmobileapps.konnectivity.NetworkConnection
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectSideEffect
import ru.melnikov.swapiapp.R
import ru.melnikov.swapiapp.domain.models.Film
import ru.melnikov.swapiapp.presentation.components.EmptyContent
import ru.melnikov.swapiapp.presentation.components.MainFilmsContent
import ru.melnikov.swapiapp.presentation.components.NoInternetDialog
import ru.melnikov.swapiapp.presentation.components.SearchField
import ru.melnikov.swapiapp.presentation.components.ShimmerCardItem
import ru.melnikov.swapiapp.presentation.state.FilmsSideEffects
import ru.melnikov.swapiapp.presentation.state.FilmsState
import ru.melnikov.swapiapp.presentation.viewmodels.FilmsViewModel

@Composable
fun FilmsScreen(
    viewModel: FilmsViewModel = koinViewModel(),
    navigateToPersons: (List<Int>, String, Int) -> Unit
) {

    val state by viewModel.container.stateFlow.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    viewModel.collectSideEffect {
        when (it) {
            is FilmsSideEffects.NavigateToPersons -> {
                navigateToPersons(it.peopleIds, it.filmTitle, it.filmId)
            }

            is FilmsSideEffects.ShowError -> {
                scope.launch {
                    snackbarHostState.showSnackbar(it.text)
                }
            }
        }
    }

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = state.isLoading
    )

    FilmsScreenContent(
        state = state,
        onRefreshSwipe = viewModel::getFilms,
        swipeRefreshState = swipeRefreshState,
        snackbarHostState = snackbarHostState,
        searchTitle = viewModel.searchTitle,
        onSearchChange = viewModel::updateSearchTitle,
        onClearClick = viewModel::onClearTitleSearch,
        onFilmClick = viewModel::onFilmClick
    )

}

@Composable
fun FilmsScreenContent(
    state: FilmsState,
    onRefreshSwipe: (Boolean) -> Unit,
    swipeRefreshState: SwipeRefreshState,
    snackbarHostState: SnackbarHostState,
    searchTitle: String,
    onSearchChange: (String) -> Unit,
    onClearClick: () -> Unit,
    onFilmClick: (List<Int>, String, Int) -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->

        NoInternetDialog()

        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }
                    )
                }
        ) {

            SearchField(
                searchName = searchTitle,
                onSearchChange = onSearchChange,
                onClearClick = onClearClick,
                focusManager = focusManager,
                keyboardController = keyboardController,
                enabled = state.films.isNotEmpty()
            )

            AnimatedContent(
                targetState = state,
                transitionSpec = {
                    fadeIn(animationSpec = tween(500)) togetherWith fadeOut(
                        animationSpec = tween(
                            500
                        )
                    )
                },
                label = ""
            ) { targetState ->
                when {
                    targetState.isLoading && targetState.films.isEmpty() -> {
                        Column(modifier = Modifier.fillMaxSize()) {
                            repeat(6) {
                                ShimmerCardItem()
                            }
                        }
                    }

                    !targetState.isLoading && targetState.films.isEmpty() -> {
                        EmptyContent(
                            text = stringResource(id = R.string.empty_data),
                            onBtnRepeatSearchClick = { onRefreshSwipe(true) })
                    }

                    else -> {
                        MainFilmsContent(
                            onRefreshSwipe = onRefreshSwipe,
                            swipeRefreshState = swipeRefreshState,
                            films = state.films,
                            searchTitle = searchTitle,
                            onFilmClick = onFilmClick
                        )
                    }
                }
            }
        }
    }
}
