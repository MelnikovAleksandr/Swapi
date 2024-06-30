package ru.melnikov.swapiapp.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.melnikov.swapiapp.presentation.viewmodels.FilmsViewModel

val uiModule = module {

    viewModel {
        FilmsViewModel(
            filmRepository = get(),
            stringResourceProvider = get()
        )
    }

}