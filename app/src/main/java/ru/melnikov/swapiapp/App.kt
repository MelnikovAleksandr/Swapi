package ru.melnikov.swapiapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.melnikov.swapiapp.data.di.dataModule
import ru.melnikov.swapiapp.presentation.di.uiModule
import ru.melnikov.swapiapp.utils.utilsModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule, uiModule, utilsModule)
        }
    }
}