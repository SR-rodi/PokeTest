package ru.sr.poketest.data.network.model

import kotlinx.serialization.SerialName

class PokemonNameNO(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)
