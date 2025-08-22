package ru.sr.poketest.presentation.screen.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sr.poketest.domain.model.PokemonColor
import ru.sr.poketest.domain.model.getColor
import ru.sr.poketest.presentation.screen.search.model.PokemonDetailsVO
import ru.sr.poketest.presentation.screen.search.model.SearchedState
import ru.sr.poketest.presentation.uiKit.commonComponent.PokemonCard
import ru.sr.poketest.presentation.uiKit.theme.PokeTheme

@Composable
fun PokemonSearchedContent(searchedState: SearchedState.Content) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = searchedState.pokemon.name,
                style = PokeTheme.typography.h2,
                textAlign = TextAlign.Center
            )
        }
        items(searchedState.pokemon.sprites) { sprite ->
            PokemonCard(
                imageUrl = sprite,
                color = searchedState.pokemon.color.getColor(),
                name = ""
            )
        }
        item {
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonSearchedContentPreview() {
    PokeTheme {
        PokemonSearchedContent(
            searchedState = SearchedState.Content(
                pokemon = PokemonDetailsVO(
                    name = "test",
                    sprites = List(6) { "" },
                    color = PokemonColor.BLUE
                )
            )
        )
    }
}
