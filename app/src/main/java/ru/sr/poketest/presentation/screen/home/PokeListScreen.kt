package ru.sr.poketest.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.flowOf
import ru.sr.poketest.R
import ru.sr.poketest.domain.model.PokemonColor
import ru.sr.poketest.domain.model.getColor
import ru.sr.poketest.presentation.screen.home.model.PokemonVO
import ru.sr.poketest.presentation.uiKit.button.CircleIconButton
import ru.sr.poketest.presentation.uiKit.commonComponent.PokemonCard
import ru.sr.poketest.presentation.uiKit.progress.DefaultErrorPage
import ru.sr.poketest.presentation.uiKit.progress.DefaultProgressIndicator
import ru.sr.poketest.presentation.uiKit.theme.PokeTheme

@Composable
fun PokeListScreen(
    pagingItems: LazyPagingItems<PokemonVO>,
    onSearchClick: () -> Unit
) {

    Box(Modifier.fillMaxSize()) {

        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            item(span = { GridItemSpan(2) }) {
                Image(
                    modifier = Modifier
                        .padding(16.dp)
                        .heightIn(max = 100.dp),
                    painter = painterResource(R.drawable.pokemon_text_logo),
                    contentDescription = "pokemon text logo",
                )
            }

            items(pagingItems.itemCount) { index ->
                when (pagingItems.loadState.refresh) {
                    is LoadState.Error -> {
                        DefaultErrorPage(
                            title = stringResource(R.string.pokemon_list_error_title),
                            subtitle = stringResource(R.string.pokemon_list_error_subtitle)
                        )
                    }

                    LoadState.Loading -> {
                        DefaultProgressIndicator()
                    }

                    is LoadState.NotLoading -> {
                        pagingItems[index]?.let { pokemon ->
                            PokemonCard(
                                name = pokemon.name,
                                color = pokemon.color.getColor(),
                                imageUrl = pokemon.imageUrl
                            )
                        }
                    }
                }
            }
        }

        CircleIconButton(
            modifier = Modifier
                .padding(28.dp)
                .align(Alignment.BottomEnd),
            imageVector = Icons.Default.Search,
            onClick = onSearchClick
        )
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
private fun PokeListPreview() {
    PokeTheme {
        val pagingData = PagingData.from(
            listOf(
                PokemonVO(
                    name = "bulbasaur",
                    imageUrl = "",
                    color = PokemonColor.BLUE
                ),
                PokemonVO(
                    name = "bulbasaur",
                    imageUrl = "",
                    color = PokemonColor.BLUE
                ),
                PokemonVO(
                    name = "bulbasaur",
                    imageUrl = "",
                    color = PokemonColor.BLUE
                )
            )
        )
        val pagingItems = flowOf(pagingData).collectAsLazyPagingItems()
        PokeListScreen(
            pagingItems = pagingItems,
            onSearchClick = {}
        )
    }
}