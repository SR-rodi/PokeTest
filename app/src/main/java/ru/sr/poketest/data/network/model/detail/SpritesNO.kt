package ru.sr.poketest.data.network.model.detail

import kotlinx.serialization.SerialName

class SpritesNO(
    @SerialName("back_default")
    val back_default: String?,
    @SerialName("back_female")
    val back_female: String?,
    @SerialName("back_shiny")
    val back_shiny: String?,
    @SerialName("back_shiny_female")
    val back_shiny_female: String?,
    @SerialName("front_default")
    val front_default: String?,
    @SerialName("front_female")
    val front_female: String?,
    @SerialName("front_shiny")
    val front_shiny: String?,
    @SerialName("front_shiny_female")
    val front_shiny_female: String?,
) {
    fun toList(): List<String?> {
        return listOf(
            back_default,
            back_female,
            back_shiny,
            back_shiny_female,
            front_default,
            front_female,
            front_shiny,
            front_shiny_female
        )
    }
}
/*
"back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png",
"back_female": null,
"back_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/1.png",
"back_shiny_female": null,
"front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
"front_female": null,
"front_shiny": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
"front_shiny_female": null,*/
