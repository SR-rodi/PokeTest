package ru.sr.poketest.presentation.screen.home.model

import ru.sr.poketest.domain.model.Pokemon
import ru.sr.poketest.domain.model.PokemonColor

class PokemonVO(
    val name: String,
    val imageUrl: String,
    val color: PokemonColor
) {
    companion object {
        fun fromDomain(model: Pokemon): PokemonVO {
            return PokemonVO(
                name = model.name,
                imageUrl = model.imageUrl,
                color = model.color
            )
        }
    }
}
