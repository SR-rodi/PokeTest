package ru.sr.poketest.presentation.screen.home.model

sealed interface PokemonListEvent {
    object OnNavigateToSearchScreen : PokemonListEvent
}
