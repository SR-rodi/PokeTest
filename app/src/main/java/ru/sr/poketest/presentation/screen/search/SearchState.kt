package ru.sr.poketest.presentation.screen.search

sealed interface SearchState {
    object Loading : SearchState
    object Error : SearchState
    object EmptySearchInput : SearchState
    data class Content(
        val pokemon: PokemonDetailsVO? = null,
        val searchValue: String = String()
    ) : SearchState
}