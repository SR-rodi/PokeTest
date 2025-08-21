package ru.sr.poketest.presentation.uiKit.image

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.SubcomposeAsyncImage
import ru.sr.poketest.presentation.uiKit.progress.DefaultProgressIndicator

@Composable
fun ImageWithPlaceholder(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = imageUrl,
        loading = {
            DefaultProgressIndicator()
        },
        error = {
            Text("Ошибка загрузки")
        },
        contentDescription = "Image from URL",
    )
}