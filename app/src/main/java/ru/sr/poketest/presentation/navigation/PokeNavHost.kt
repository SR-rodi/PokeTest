package ru.sr.poketest.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.sr.poketest.presentation.screen.home.HomeViewModel
import ru.sr.poketest.presentation.screen.home.PokeListScreen

fun NavGraphBuilder.pokeNavHost(navController: NavController) {

    composable<PokeDestination.PokemonList> {
        val viewModel: HomeViewModel = koinViewModel()
        val state = viewModel.pokemonState.collectAsState()

        PokeListScreen(
            pagingItems = state.value.pagingFlow.collectAsLazyPagingItems(),
            onSearchClick = {
                navController.navigate(PokeDestination.Search)
            }
        )
    }
    composable<PokeDestination.Search> {

    }
}