package ru.melnikov.swapiapp.presentation.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectSideEffect
import ru.melnikov.swapiapp.R
import ru.melnikov.swapiapp.presentation.components.EmptyContent
import ru.melnikov.swapiapp.presentation.components.MainPlanetContent
import ru.melnikov.swapiapp.presentation.components.NoInternetDialog
import ru.melnikov.swapiapp.presentation.components.PlanetLoader
import ru.melnikov.swapiapp.presentation.components.TopBar
import ru.melnikov.swapiapp.presentation.state.PlanetSideEffects
import ru.melnikov.swapiapp.presentation.state.PlanetState
import ru.melnikov.swapiapp.presentation.viewmodels.PlanetViewModel

@Composable
fun PlanetScreen(
    viewModel: PlanetViewModel = koinViewModel(),
    navigateBack: () -> Unit
) {

    val state by viewModel.container.stateFlow.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    viewModel.collectSideEffect {
        when (it) {
            is PlanetSideEffects.NavigateBack -> navigateBack()

            is PlanetSideEffects.ShowError -> {
                scope.launch {
                    snackbarHostState.showSnackbar(it.text)
                }
            }
        }
    }

    PlanetScreenContent(
        state = state,
        snackbarHostState = snackbarHostState,
        navigateBack = viewModel::navigateBack,
        updateScreen = viewModel::getPlanet
    )
}

@Composable
fun PlanetScreenContent(
    state: PlanetState,
    snackbarHostState: SnackbarHostState,
    navigateBack: () -> Unit,
    updateScreen: (Boolean) -> Unit
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopBar(
                title = stringResource(id = R.string.planet_info),
                navigateBack = navigateBack
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->

        NoInternetDialog()

        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
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
                    targetState.isLoading && targetState.planet == null -> {
                        PlanetLoader()
                    }

                    !targetState.isLoading && targetState.planet == null -> {
                        EmptyContent(
                            text = stringResource(id = R.string.empty_data),
                            onBtnRepeatSearchClick = { updateScreen(true) })
                    }

                    else -> {
                        state.planet?.let {
                            MainPlanetContent(planet = it)
                        }
                    }
                }
            }
        }
    }
}