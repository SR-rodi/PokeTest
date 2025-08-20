package ru.sr.poketest.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.flowOf
import ru.sr.poketest.R
import ru.sr.poketest.domain.model.PokemonColor
import ru.sr.poketest.presentation.screen.home.model.PokemonVO
import ru.sr.poketest.presentation.uiKit.image.ImageWithPlaceholder
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

            item(span = { GridItemSpan(2) }) {}

            items(pagingItems.itemCount) { index ->
                val pokemonColor = pagingItems[index]?.color ?: PokemonColor.UNKNOWN
                Box(
                    Modifier
                        .height(146.dp)
                        .clip(RoundedCornerShape(16))
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    pokemonColor.getColor(),
                                    Color.Black,
                                )
                            )
                        )
                ) {
                    pagingItems[index]?.imageUrl?.let { imageUrl ->
                        ImageWithPlaceholder(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxSize(0.6f),
                            imageUrl = imageUrl
                        )
                    }


                    Text(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                            .fillMaxWidth(),
                        text = pagingItems[index]?.name ?: "unknow",
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        style = PokeTheme.typography.p,
                        color = PokeTheme.colors.with
                    )
                }
            }
        }

        Box(
            Modifier
                .padding(26.dp)
                .size(45.dp)
                .clip(CircleShape)
                .background(PokeTheme.colors.primaryBlue)
                .shadow(elevation = 2.dp)
                .align(Alignment.BottomEnd)
                .clickable(onClick = onSearchClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = PokeTheme.colors.with,
            )
        }
    }
}

@Composable
private fun PokemonColor.getColor(): Color {
    return when (this) {
        PokemonColor.BLACK -> Color(0xFF070707)
        PokemonColor.BLUE -> Color(0xFF054D74)
        PokemonColor.BROWN -> Color(0xFF8B4513)
        PokemonColor.GRAY -> Color(0xFF808080)
        PokemonColor.GREEN -> Color(0xFF008000)
        PokemonColor.PINK -> Color(0xFFFFC0CB)
        PokemonColor.PURPLE -> Color(0xFF800080)
        PokemonColor.RED -> Color(0xFFC00A0A)
        PokemonColor.WHITE -> Color(0xFFFFFFFF)
        PokemonColor.YELLOW -> Color(0xFFFFD700)
        PokemonColor.UNKNOWN -> Color(0xFFAAAAAA)
    }
}

@Preview
@Composable
private fun PokeListPreview() {
    PokeTheme {
        val pagingData = PagingData.from(
            listOf(
                PokemonVO(
                    name = "",
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