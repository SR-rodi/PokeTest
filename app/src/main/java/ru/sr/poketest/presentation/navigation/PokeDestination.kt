package ru.sr.poketest.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface PokeDestination {

    @Serializable
    object PokemonList : PokeDestination

    @Serializable
    class Search(val name: String) : PokeDestination
}