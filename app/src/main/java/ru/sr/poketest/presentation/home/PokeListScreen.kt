package ru.sr.poketest.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.flowOf
import ru.sr.poketest.domain.model.PokemonColor
import ru.sr.poketest.presentation.uiKit.image.ImageWithPlaceholder
import ru.sr.poketest.presentation.uiKit.theme.PokeTheme

@Composable
fun PokeListScreen(pagingItems: LazyPagingItems<PokemonVO>) {

    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
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
                        modifier = Modifier.align(Alignment.Center).fillMaxSize(0.6f),
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
}

@Composable
private fun PokemonColor.getColor(): Color {
    return when (this) {
        PokemonColor.BLACK -> Color(0xFF070707) // Чёрный
        PokemonColor.BLUE -> Color(0xFF054D74)  // Синий
        PokemonColor.BROWN -> Color(0xFF8B4513) // Коричневый
        PokemonColor.GRAY -> Color(0xFF808080)  // Серый
        PokemonColor.GREEN -> Color(0xFF008000) // Зелёный
        PokemonColor.PINK -> Color(0xFFFFC0CB)  // Розовый
        PokemonColor.PURPLE -> Color(0xFF800080) // Фиолетовый
        PokemonColor.RED -> Color(0xFFC00A0A)  // Красный
        PokemonColor.WHITE -> Color(0xFFFFFFFF) // Белый
        PokemonColor.YELLOW -> Color(0xFFFFD700) // Жёлтый
        PokemonColor.UNKNOWN -> Color(0xFFAAAAAA) // Серый по умолчанию для неизвестных цветов
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
        PokeListScreen(pagingItems = pagingItems)
    }
}