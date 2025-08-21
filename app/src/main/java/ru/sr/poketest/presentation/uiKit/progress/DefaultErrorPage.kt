package ru.sr.poketest.presentation.uiKit.progress

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sr.poketest.presentation.uiKit.image.PokeBall
import ru.sr.poketest.presentation.uiKit.theme.PokeTheme

@Composable
fun DefaultErrorPage(title: String, subtitle: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PokeBall()
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                text = title,
                textAlign = TextAlign.Center,
                style = PokeTheme.typography.h3,
                color = PokeTheme.colors.textOnBackground
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = subtitle,
                textAlign = TextAlign.Center,
                style = PokeTheme.typography.h4,
                color = PokeTheme.colors.textOnBackground
            )
        }

    }
}

@Preview
@Composable
private fun DefaultProgressIndicatorPreview() {
    DefaultErrorPage("Title", "Subtitle")
}
