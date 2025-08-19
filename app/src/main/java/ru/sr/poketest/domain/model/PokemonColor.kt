package ru.sr.poketest.domain.model

enum class PokemonColor(val colorName: String) {
    BLACK("black"),
    BLUE("blue"),

    // TODO: add all colors
    UNKNOWN("UNKNOWN")
    ;

    companion object {
        fun fromString(name: String): PokemonColor {
            return PokemonColor.entries.find { it.colorName.equals(name, ignoreCase = true) } ?: UNKNOWN
        }
    }
}