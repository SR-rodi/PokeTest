package ru.sr.poketest.presentation.screen.search

import ru.sr.poketest.domain.model.PokemonColor
import ru.sr.poketest.domain.model.PokemonDetails

class PokemonDetailsVO(
    val name: String,
    val sprites: List<String>,
    val color: PokemonColor
) {
    companion object {
        fun fromDomain(model: PokemonDetails): PokemonDetailsVO {
            return PokemonDetailsVO(
                name = model.name,
                sprites = model.sprites,
                color = model.color
            )
        }
    }
}