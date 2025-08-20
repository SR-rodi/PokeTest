package ru.sr.poketest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
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
            val navController = rememberNavController()
            PokeTheme(isNightMode = true) {

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
