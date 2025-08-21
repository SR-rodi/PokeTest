package ru.sr.poketest.data.network.model.detail

import ru.sr.poketest.domain.model.PokemonColor
import ru.sr.poketest.domain.model.PokemonDetails

class PokemonDetailsNO(
    val id: Int,
    val name: String,
    val sprites: Sprites,
) {
    fun toDomain(color: PokemonColor): PokemonDetails {
       return PokemonDetails(
            name = name,
            sprites = sprites.toList().mapNotNull { it },
            color = color
        )
    }
}