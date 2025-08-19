package ru.sr.poketest.domain.repository

import ru.sr.poketest.domain.model.Pokemon

interface PokemonRepository {
   suspend fun getPokemon(offset: Int, limit: Int): Result<List<Pokemon>>
}