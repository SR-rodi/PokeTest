package ru.sr.poketest.presentation.uiKit.defaultPage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.sr.poketest.presentation.uiKit.progress.DefaultProgressIndicator

@Composable
fun DefaultLoadingPage() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        DefaultProgressIndicator()
    }
}

@Preview
@Composable
private fun DefaultLoadingPagePreview() {
    DefaultLoadingPage()
}