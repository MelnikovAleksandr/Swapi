package ru.melnikov.swapiapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ru.melnikov.swapiapp.presentation.theme.SwapiAppTheme
import ru.melnikov.swapiapp.presentation.navigation.NavigationGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.rootView.setBackgroundColor(Color.Transparent.toArgb())
        actionBar?.hide()
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            SwapiAppTheme {
                NavigationGraph()
            }
        }
    }
}
