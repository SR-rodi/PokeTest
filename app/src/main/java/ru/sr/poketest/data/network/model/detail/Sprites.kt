package ru.sr.poketest.data.network.model.detail

class Sprites(
    val back_default: String,
    val back_female: String,
    val back_shiny: String,
    val back_shiny_female: String,
    val front_default: String,
    val front_female: String,
    val front_shiny: String,
    val front_shiny_female: String,
) {
    fun toList(): List<String> {
        return listOf(
            front_default,
            back_default,
            back_female,
            back_shiny,
            back_shiny_female,
            front_female,
            front_shiny,
            front_shiny_female
        )
    }
}