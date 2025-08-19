package ru.sr.poketest.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import ru.sr.poketest.data.network.PokeApi
import ru.sr.poketest.domain.model.Pokemon
import ru.sr.poketest.domain.model.PokemonColor
import ru.sr.poketest.domain.repository.PokemonRepository

class PokemonRepositoryImpl(
    private val api: PokeApi
) : PokemonRepository {

    override suspend fun getAllPokemon(offset: Int, limit: Int): Result<List<Pokemon>> {
        return runCatching {
            val name = api.getAllPokemon(offset = offset, limit = limit)
            name.results.asyncMap { pokemon ->
                val pokemonColor = api.getSpecies(pokemon.name).color.name
                Pokemon(
                    name = pokemon.name,
                    imageUrl = pokemon.url.getPicUrl(),
                    color = PokemonColor.fromString(pokemonColor)
                )
            }
        }
    }

    private fun String.getPicUrl(): String {
        val id = this.substringAfter("pokemon").replace("/", "")
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
    }

    private suspend fun <T, R> List<T>.asyncMap(transform: suspend (T) -> R): List<R> {
        return coroutineScope {
            this@asyncMap.map { item ->
                async { transform(item) }
            }.awaitAll()
        }
    }
}
