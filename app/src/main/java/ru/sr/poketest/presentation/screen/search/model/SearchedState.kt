package ru.sr.poketest.presentation.screen.search.model

import ru.sr.poketest.domain.model.PokemonColor

sealed interface SearchedState {
    object Loading : SearchedState
    object Error : SearchedState
    object EmptySearchInput : SearchedState
    data class Content(
        val pokemon: PokemonDetailsVO = PokemonDetailsVO(
            name = "",
            sprites = emptyList(),
            color = PokemonColor.UNKNOWN
        ),
    ) : SearchedState
}
