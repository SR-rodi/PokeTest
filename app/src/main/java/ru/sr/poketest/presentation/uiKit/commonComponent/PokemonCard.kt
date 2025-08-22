package ru.sr.poketest.presentation.uiKit.commonComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import ru.sr.poketest.presentation.uiKit.image.ImageWithPlaceholder
import ru.sr.poketest.presentation.uiKit.theme.PokeTheme

@Composable
fun PokemonCard(
    imageUrl: String,
    color: Color,
    name: String
) {
    Box(
        Modifier
            .height(146.dp)
            .clip(RoundedCornerShape(16))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        color,
                        Color.Black,
                    )
                )
            )
    ) {
        ImageWithPlaceholder(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(0.6f)
                .padding(bottom = 8.dp),
            imageUrl = imageUrl
        )



        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
                .fillMaxWidth(),
            text = name,
            textAlign = TextAlign.Center,
            maxLines = 1,
            style = PokeTheme.typography.p,
            color = PokeTheme.colors.with
        )
    }
}

@Preview
@Composable
private fun PokemonCardPreview() {
    PokeTheme {
        PokemonCard(
            color = Color.Red,
            name = "charmander",
            imageUrl = ""
        )
    }
}
