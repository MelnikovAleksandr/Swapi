package ru.melnikov.swapiapp.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.melnikov.swapiapp.presentation.screens.FilmsScreen

@Composable
fun NavigationGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.FilmsScreen,
        modifier = Modifier.fillMaxSize()
    ) {

        composable<Routes.FilmsScreen> {
            FilmsScreen()
        }

        composable<Routes.PeopleScreen> {

        }

        composable<Routes.PlanetsScreen> {

        }

    }
}