package ru.sr.poketest.presentation.uiKit.image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.SubcomposeAsyncImage

@Composable
fun ImageWithPlaceholder(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = imageUrl,
        loading = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                LinearProgressIndicator()
            }

        },
        error = {
            Text("Ошибка загрузки")
        },
        contentDescription = "Image from URL",
    )
}