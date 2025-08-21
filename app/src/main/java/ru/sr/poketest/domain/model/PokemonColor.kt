package ru.sr.poketest.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class PokemonColor(val colorName: String) {
    BLACK("black"),
    BLUE("blue"),
    BROWN("brown"),
    GRAY("gray"),
    GREEN("green"),
    PINK("pink"),
    PURPLE("purple"),
    RED("red"),
    WHITE("white"),
    YELLOW("yellow"),
    UNKNOWN("UNKNOWN");

    companion object {
        fun fromString(nameColor: String): PokemonColor {
            return PokemonColor.entries.find { value ->
                value.colorName.equals(nameColor, ignoreCase = true)
            } ?: UNKNOWN
        }
    }
}

@Composable
fun PokemonColor.getColor(): Color {
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