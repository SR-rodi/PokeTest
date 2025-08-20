package ru.sr.poketest.domain.model

class Pokemon(
    val name: String,
    val imageUrl: String,
    val color: PokemonColor
)

class PokemonDetails(
    val name: String,
    val sprites: List<String>,
    val color: PokemonColor
)


