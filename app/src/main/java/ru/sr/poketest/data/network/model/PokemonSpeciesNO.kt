package ru.sr.poketest.data.network.model

import kotlinx.serialization.SerialName

class PokemonSpeciesNO(
    @SerialName("color")
    val color: ColorNO,
    @SerialName("id")
    val id: Int,
)
