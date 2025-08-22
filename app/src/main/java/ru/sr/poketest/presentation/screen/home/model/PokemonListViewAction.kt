package ru.sr.poketest.presentation.screen.home.model

import ru.sr.poketest.domain.model.PokemonColor

sealed interface PokemonListViewAction {
    object OnSearchButtonClick : PokemonListViewAction
    class OnChangeSelectedColor(val color: PokemonColor?) : PokemonListViewAction
}
