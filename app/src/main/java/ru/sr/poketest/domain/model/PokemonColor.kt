package ru.sr.poketest.domain.model

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