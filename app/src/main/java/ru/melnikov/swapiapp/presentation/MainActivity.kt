package ru.melnikov.swapiapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.melnikov.swapiapp.presentation.navigation.NavigationGraph
import ru.melnikov.swapiapp.presentation.theme.SwapiAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwapiAppTheme {
                NavigationGraph()
            }
        }
    }
}
