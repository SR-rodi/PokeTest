package ru.sr.poketest.presentation.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sr.poketest.presentation.screen.search.component.PokemonSearchedContent
import ru.sr.poketest.presentation.screen.search.model.SearchViewAction
import ru.sr.poketest.presentation.screen.search.model.SearchViewState
import ru.sr.poketest.presentation.screen.search.model.SearchedState
import ru.sr.poketest.presentation.uiKit.image.PokeBall
import ru.sr.poketest.presentation.uiKit.navbar.Navbar
import ru.sr.poketest.presentation.uiKit.progress.DefaultProgressIndicator
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
            title = "Поиск по имени",
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
                    text = "Найти",
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
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp),
                    text = "Данное поле не должно быть пустым",
                    style = PokeTheme.typography.p,
                    color = PokeTheme.colors.primaryBlueVariant,
                    textAlign = TextAlign.Center
                )
            }

            SearchedState.Error -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        PokeBall()
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            text = "По данному запросу ничего не найдено",
                            textAlign = TextAlign.Center,
                            style = PokeTheme.typography.h3,
                            color = PokeTheme.colors.textOnBackground
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            text = "Проверте введенное имя или попробуйте позже",
                            textAlign = TextAlign.Center,
                            style = PokeTheme.typography.h4,
                            color = PokeTheme.colors.textOnBackground
                        )
                    }

                }

            }

            SearchedState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    DefaultProgressIndicator()
                }
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