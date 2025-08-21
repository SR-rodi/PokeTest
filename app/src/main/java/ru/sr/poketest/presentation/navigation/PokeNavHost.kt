package ru.sr.poketest.presentation.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.sr.poketest.presentation.screen.home.HomeViewModel
import ru.sr.poketest.presentation.screen.home.PokeListScreen
import ru.sr.poketest.presentation.screen.search.model.SearchEvent
import ru.sr.poketest.presentation.screen.search.SearchScreen
import ru.sr.poketest.presentation.screen.search.SearchVewModel

fun NavGraphBuilder.pokeNavHost(navController: NavController) {

    composable<PokeDestination.PokemonList> {
        val viewModel: HomeViewModel = koinViewModel()
        val state = viewModel.viewState.collectAsState()

        PokeListScreen(
            pagingItems = state.value.pagingFlow.collectAsLazyPagingItems(),
            onSearchClick = {
                navController.navigate(PokeDestination.Search)
            }
        )
    }
    composable<PokeDestination.Search> {
        val viewModel: SearchVewModel = koinViewModel()
        val state = viewModel.viewState.collectAsState().value
        val event = viewModel.viewEvent.collectAsState(null)

        LaunchedEffect(event.value) {
            if (event.value == SearchEvent.NavigateToPopUp) {
                navController.popBackStack()
            }
        }

        SearchScreen(
            state = state,
            onAction = { action ->
                viewModel.onActionHandle(action)
            }
        )
    }
}