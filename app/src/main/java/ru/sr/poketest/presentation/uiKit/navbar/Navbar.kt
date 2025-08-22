package ru.sr.poketest.presentation.uiKit.navbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sr.poketest.presentation.uiKit.theme.PokeTheme

@Composable
fun Navbar(
    title: String,
    onClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(18.dp)
                .clickable(onClick = onClick),
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "ArrowBack",
            tint = PokeTheme.colors.textOnBackground,
        )

        Text(
            modifier = Modifier.weight(1f),
            text = title,
            textAlign = TextAlign.Center,
            maxLines = 1,
            style = PokeTheme.typography.h4,
            color = PokeTheme.colors.textOnBackground
        )
    }
}

@Preview
@Composable
private fun NavbarPreview() {
    PokeTheme {
        Navbar(title = "title", onClick = {})
    }
}