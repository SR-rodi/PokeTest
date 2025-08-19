package ru.sr.poketest.domain.model

enum class PokemonColor(val colorName: String) {
    BLACK("black"),
    BLUE("blue"),

    // TODO: add all colors
    UNKNOWN("UNKNOWN")
    ;

    companion object {
        fun fromString(nameColor: String): PokemonColor {
            return PokemonColor.entries.find { value ->
                value.colorName.equals(nameColor, ignoreCase = true)
            } ?: UNKNOWN
        }
    }
}