package ru.sr.poketest.presentation.uiKit.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class PokeColor(
    val with: Color = Color(0xFFFFFFFF),                        // постоянно белый
    val primaryBlue: Color,                // основной синий (deepBlue в светлой, lightBlue в тёмной)
    val primaryBlueVariant: Color,         // вариация синего (lightBlue в светлой, mediumBlue в тёмной)
    val accentBlue: Color,                 // акцентный синий (accentCyan в светлой, lightCyan в тёмной)
    val darkBlue: Color,                  // тёмный синий (darkAccentBlue в обеих темах)
    val background: Color,                // фон (white в светлой, darkGray в тёмной)
    val surface: Color,                   // поверхность (lightGray в светлой, darkerGray в тёмной)
    val errorRed: Color,                  // ошибка (brightRed в светлой, softRed в тёмной)
    val textOnPrimary: Color,             // текст на primary (whiteText в светлой, darkText в тёмной)
    val textOnSecondary: Color,           // текст на secondary (darkText в светлой, whiteText в тёмной)
    val textOnBackground: Color,          // текст на фоне (darkText в светлой, lightText в тёмной)
    val textOnSurface: Color,             // текст на поверхности (darkText в светлой, lightText в тёмной)
    val textOnError: Color,               // текст на ошибке (whiteText в светлой, darkText в тёмной)
)

val LightPokePalette = PokeColor(
    primaryBlue = Color(0xFF054D74),   // глубокий синий
    primaryBlueVariant = Color(0xFF749CD7),  // более светлый синий
    accentBlue = Color(0xFF46E8FD),  // акцентный голубой
    darkBlue = Color(0xFF0B5DA3),  // тёмный акцент
    background = Color(0xFFFFFFFF), // белый фон
    surface = Color(0xFFF2F2F2), // светло-серый “поверхности”
    errorRed = Color(0xFFC00A0A), // ярко-красный
    textOnPrimary = Color(0xFFFFFFFF), // белый текст
    textOnSecondary = Color(0xFF070707),  // тёмный текст
    textOnBackground = Color(0xFF070707),  // основной текст
    textOnSurface = Color(0xFF070707),  // текст на поверхностях
    textOnError = Color(0xFFFFFFFF),  // текст на ошибке
)

val DarkPokePalette = PokeColor(
    primaryBlue = Color(0xFF9CB9D1),   // светло-синий
    primaryBlueVariant = Color(0xFF749CD7),  // средний синий
    accentBlue = Color(0xFF46E8FD),   // акцентный голубой
    darkBlue = Color(0xFF0B5DA3),   // тёмный акцент
    background = Color(0xFF121212), // почти чёрный фон
    surface = Color(0xFF1E1E1E),   // тёмно-серый “поверхности”
    errorRed = Color(0xFFEF5350),  // мягкий красный
    textOnPrimary = Color(0xFF070707),   // тёмный текст
    textOnSecondary = Color(0xFFFFFFFF),   // светлый текст
    textOnBackground = Color(0xFFFFFFFF),   // основной текст
    textOnSurface = Color(0xFFFFFFFF),   // текст на поверхностях
    textOnError = Color(0xFF000000),   // тёмный текст
)
val LocalColorProvider =
    staticCompositionLocalOf<PokeColor> { error("No default implementation") }