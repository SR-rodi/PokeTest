package ru.sr.poketest.domain.repository

import ru.sr.poketest.domain.model.Pokemon
import ru.sr.poketest.domain.model.PokemonDetails

interface PokemonRepository {
   suspend fun getPokemon(offset: Int, limit: Int): Result<List<Pokemon>>
   suspend fun getPokemonByName(name: String): Result<PokemonDetails>
}
