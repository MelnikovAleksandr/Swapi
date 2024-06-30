package ru.melnikov.swapiapp.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.melnikov.swapiapp.presentation.viewmodels.FilmsViewModel
import ru.melnikov.swapiapp.presentation.viewmodels.PeopleViewModel
import ru.melnikov.swapiapp.presentation.viewmodels.PlanetViewModel

val uiModule = module {

    viewModel {
        FilmsViewModel(
            filmRepository = get(),
            stringResourceProvider = get()
        )
    }

    viewModel {
        PeopleViewModel(
            peopleRepository = get(),
            stringResourceProvider = get(),
            savedStateHandle = get()
        )
    }

    viewModel {
        PlanetViewModel(
            planetRepository = get(),
            stringResourceProvider = get(),
            savedStateHandle = get()
        )
    }

}