package ru.sr.poketest.data.network.model

data class PokemonListResponseNO(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonNameNO>
)

