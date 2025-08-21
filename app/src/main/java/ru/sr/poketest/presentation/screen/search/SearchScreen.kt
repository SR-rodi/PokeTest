package ru.sr.poketest.presentation.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sr.poketest.R
import ru.sr.poketest.presentation.screen.search.component.PokemonSearchedContent
import ru.sr.poketest.presentation.screen.search.model.SearchViewAction
import ru.sr.poketest.presentation.screen.search.model.SearchViewState
import ru.sr.poketest.presentation.screen.search.model.SearchedState
import ru.sr.poketest.presentation.uiKit.defaultPage.DefaultLoadingPage
import ru.sr.poketest.presentation.uiKit.navbar.Navbar
import ru.sr.poketest.presentation.uiKit.progress.DefaultErrorPage
import ru.sr.poketest.presentation.uiKit.search.SearchInput
import ru.sr.poketest.presentation.uiKit.theme.PokeTheme

@Composable
fun SearchScreen(
    state: SearchViewState,
    onAction: (action: SearchViewAction) -> Unit,
) {

    Column(
        modifier = Modifier
            .background(PokeTheme.colors.background)
    ) {
        Navbar(
            title = stringResource(R.string.pokemon_serch_screen_title),
            onClick = { onAction.invoke(SearchViewAction.OnBackArrowClick) }
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchInput(
                modifier = Modifier.weight(1f),
                value = state.searchValue,
                placeholder = stringResource(R.string.pokemon_search_placeholder),
                onValueChange = { onAction.invoke(SearchViewAction.OnChangeSearchValue(it)) }
            )

            Spacer(Modifier.width(8.dp))

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = PokeTheme.colors.primaryBlue
                ),
                modifier = Modifier.height(30.dp),
                onClick = {
                    onAction.invoke(SearchViewAction.OnSearchButtonClick)
                }) {
                Text(
                    text = stringResource(R.string.pokemon_search_button),
                    style = PokeTheme.typography.p
                )
            }
        }

        when (val searchedState = state.searchedState) {
            is SearchedState.Content -> {
                PokemonSearchedContent(searchedState)
            }

            SearchedState.EmptySearchInput -> {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    text = stringResource(R.string.pokemon_search_helper_text),
                    style = PokeTheme.typography.p,
                    color = PokeTheme.colors.primaryBlueVariant,
                    textAlign = TextAlign.Center
                )
            }

            SearchedState.Error -> {
                DefaultErrorPage(
                    title = stringResource(R.string.pokemon_search_error_title),
                    subtitle = stringResource(R.string.pokemon_search_error_subtitle)
                )
            }

            SearchedState.Loading -> {
                DefaultLoadingPage()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    PokeTheme {
        SearchScreen(
            state = SearchViewState(
                searchValue = "Poke",
                searchedState = SearchedState.Error
            ),
            onAction = {}
        )
    }
}