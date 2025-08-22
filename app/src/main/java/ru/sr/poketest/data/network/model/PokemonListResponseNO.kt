package ru.sr.poketest.data.network.model

import kotlinx.serialization.SerialName

class PokemonListResponseNO(
    @SerialName("results")
    val results: List<PokemonNameNO>
)
