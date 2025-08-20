package ru.sr.poketest.presentation

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.sr.poketest.presentation.navigation.PokeDestination
import ru.sr.poketest.presentation.navigation.pokeNavHost
import ru.sr.poketest.presentation.uiKit.theme.PokeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val isSystemInDarkTheme = isSystemInDarkTheme()
            WindowCompat.getInsetsController(window, window.decorView).apply {
                isAppearanceLightStatusBars = !isSystemInDarkTheme
            }

            val navController = rememberNavController()
            PokeTheme(isSystemInDarkTheme) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(PokeTheme.colors.background)
                ) {
                    Box(
                        Modifier
                            .systemBarsPadding()
                            .fillMaxSize()
                    ) {
                        NavHost(
                            startDestination = PokeDestination.PokemonList,
                            navController = navController
                        ) {
                            pokeNavHost(navController)
                        }
                    }
                }
            }
        }
    }
}
