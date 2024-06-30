package ru.melnikov.swapiapp.presentation.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectSideEffect
import ru.melnikov.swapiapp.R
import ru.melnikov.swapiapp.presentation.components.EmptyContent
import ru.melnikov.swapiapp.presentation.components.MainPeopleContent
import ru.melnikov.swapiapp.presentation.components.ShimmerCardItem
import ru.melnikov.swapiapp.presentation.components.TopBar
import ru.melnikov.swapiapp.presentation.state.PeopleSideEffects
import ru.melnikov.swapiapp.presentation.state.PeopleState
import ru.melnikov.swapiapp.presentation.viewmodels.PeopleViewModel

@Composable
fun PeopleScreen(
    viewModel: PeopleViewModel = koinViewModel(),
    navigateBack: () -> Unit
) {

    val state by viewModel.container.stateFlow.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    viewModel.collectSideEffect {
        when (it) {
            is PeopleSideEffects.NavigateToPlanet -> {

            }

            is PeopleSideEffects.NavigateBack -> navigateBack()

            is PeopleSideEffects.ShowError -> {
                scope.launch {
                    snackbarHostState.showSnackbar(it.text)
                }
            }
        }
    }

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = state.isLoading
    )

    PeopleScreenContent(
        state = state,
        onRefreshSwipe = viewModel::getPeople,
        swipeRefreshState = swipeRefreshState,
        snackbarHostState = snackbarHostState,
        navigateBack = viewModel::navigateBack
    )

}

@Composable
fun PeopleScreenContent(
    state: PeopleState,
    onRefreshSwipe: (Boolean) -> Unit,
    swipeRefreshState: SwipeRefreshState,
    snackbarHostState: SnackbarHostState,
    navigateBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopBar(
                filmTitle = state.filmTitle,
                navigateBack = navigateBack
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->

        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

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
                    targetState.isLoading && targetState.people.isEmpty() -> {
                        Column(modifier = Modifier.fillMaxSize()) {
                            repeat(6) {
                                ShimmerCardItem()
                            }
                        }
                    }

                    !targetState.isLoading && targetState.people.isEmpty() -> {
                        EmptyContent(
                            text = stringResource(id = R.string.empty_data),
                            onBtnRepeatSearchClick = { onRefreshSwipe(true) })
                    }

                    else -> {
                        MainPeopleContent(
                            onRefreshSwipe = onRefreshSwipe,
                            swipeRefreshState = swipeRefreshState,
                            people = state.people
                        )
                    }
                }
            }

        }
    }
}