package ru.sr.poketest.presentation.uiKit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat


@Composable
fun PokeTheme(
    isNightMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {

    MaterialTheme(colorScheme = MaterialTheme.colorScheme.copy(onPrimaryContainer = Color(0xFFFFFFFF))) {
        val colors = if (isNightMode) DarkPokePalette else LightPokePalette
        CompositionLocalProvider(
            LocalColorProvider provides colors,
            LocalFontProvider provides fonts,
            content = content
        )
    }
}

object PokeTheme {
    val colors: PokeColor
        @Composable
        get() = LocalColorProvider.current
    val typography: PokeTypography
        @Composable
        get() = LocalFontProvider.current
}