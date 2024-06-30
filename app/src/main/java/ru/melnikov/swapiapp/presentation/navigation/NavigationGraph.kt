package ru.melnikov.swapiapp.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.melnikov.swapiapp.presentation.screens.FilmsScreen
import ru.melnikov.swapiapp.presentation.screens.PeopleScreen
import ru.melnikov.swapiapp.presentation.screens.PlanetScreen

@Composable
fun NavigationGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.FilmsScreen,
        modifier = Modifier.fillMaxSize()
    ) {

        composable<Routes.FilmsScreen> {
            FilmsScreen(
                navigateToPersons = { people, title, id ->
                    navController.navigateTo(
                        Routes.PeopleScreen(
                            people, title, id
                        )
                    )
                }
            )
        }

        composable<Routes.PeopleScreen> {
            PeopleScreen(
                navigateBack = {
                    navController.popUp()
                },
                onPlanetNavigate = { planetId ->
                    navController.navigateTo(
                        Routes.PlanetsScreen(
                            planetId = planetId
                        )
                    )
                }
            )
        }

        composable<Routes.PlanetsScreen> {
            PlanetScreen {
                navController.popUp()
            }
        }
    }
}