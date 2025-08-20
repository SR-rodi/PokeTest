package ru.sr.poketest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.sr.poketest.presentation.home.HomeViewModel
import ru.sr.poketest.presentation.home.PokeListScreen
import ru.sr.poketest.presentation.uiKit.theme.PokeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeTheme {
                Box(
                    Modifier
                        .systemBarsPadding()
                        .fillMaxSize()
                        .background(PokeTheme.colors.surface)
                ) {
                    val viewModel: HomeViewModel = koinViewModel()
                    val state = viewModel.pokemonState.collectAsState()

                    PokeListScreen(pagingItems = state.value.pagingFlow.collectAsLazyPagingItems())
                }
            }

        }
    }
}