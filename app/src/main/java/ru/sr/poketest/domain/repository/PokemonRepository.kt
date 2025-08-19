package ru.sr.poketest.domain.repository

import ru.sr.poketest.domain.model.Pokemon

interface PokemonRepository {
   suspend fun getAllPokemon(offset: Int, limit: Int): Result<List<Pokemon>>
}