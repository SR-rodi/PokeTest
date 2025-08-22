package ru.sr.poketest.data.network.model.detail

import kotlinx.serialization.SerialName
import ru.sr.poketest.domain.model.PokemonColor
import ru.sr.poketest.domain.model.PokemonDetails

class PokemonDetailsNO(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("sprites")
    val sprites: SpritesNO,
) {
    fun toDomain(color: PokemonColor): PokemonDetails {
       return PokemonDetails(
            name = name,
            sprites = sprites.toList().mapNotNull { it },
            color = color
        )
    }
}
