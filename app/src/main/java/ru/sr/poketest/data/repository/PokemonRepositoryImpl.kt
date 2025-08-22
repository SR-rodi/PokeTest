package ru.sr.poketest.data.repository

import android.util.Log
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import ru.sr.poketest.data.database.PokemonDao
import ru.sr.poketest.data.database.PokemonEntity
import ru.sr.poketest.data.network.PokeApi
import ru.sr.poketest.data.network.model.PokemonNameNO
import ru.sr.poketest.domain.model.Pokemon
import ru.sr.poketest.domain.model.PokemonColor
import ru.sr.poketest.domain.model.PokemonDetails
import ru.sr.poketest.domain.repository.PokemonRepository

class PokemonRepositoryImpl(
    private val api: PokeApi,
    private val dao: PokemonDao
) : PokemonRepository {

    override suspend fun getPokemon(offset: Int, limit: Int): Result<List<Pokemon>> {
        return runCatching {
            getPokemonFromApi(offset = offset, limit = limit)
        }.fold(
            onSuccess = { pokemon ->
                Result.success(pokemon)
            },
            onFailure = { exception ->
                val pokemon = getPokemonFromDatabase(offset = offset, limit = limit)
                Result.success(pokemon)
            }
        )
    }

    override suspend fun getPokemonByName(name: String): Result<PokemonDetails> {
        return runCatching {
            api.getPokemonByName(name)
        }.fold(
            onSuccess = { details ->
                Result.success(
                    details.toDomain(
                        color = PokemonColor.fromString(getPokemonColor(name))
                    )
                )
            },
            onFailure = {
                val details = dao.getPokemonByName(name)?.toDetails()
                if (details == null) {
                    Result.failure(NullPointerException("Pokemon '$name' not found"))
                } else {
                    Result.success(details)
                }
            }
        )
    }

    private suspend fun getPokemonFromApi(offset: Int, limit: Int): List<Pokemon> {
        val response = api.getAllPokemon(offset = offset, limit = limit)
        val pokemonList = response.results.asyncMap { pokemon ->
            mapToDomainPokemon(pokemon)
        }
        savePokemonToDatabase(pokemonList)
        return pokemonList
    }

    private suspend fun savePokemonToDatabase(pokemon: List<Pokemon>) {
        dao.insertAll(pokemon.map { PokemonEntity.fromDomain(it) })
    }

    private suspend fun mapToDomainPokemon(pokemon: PokemonNameNO): Pokemon {
        val color = getPokemonColor(pokemon.name)

        return Pokemon(
            name = pokemon.name,
            imageUrl = pokemon.url.getPicUrl(),
            color = PokemonColor.fromString(color)
        )
    }

    private suspend fun getPokemonColor(name: String): String {
        val color = runCatching {
            getPokemonColorByName(name)
        }.getOrDefault("")
        return color
    }

    private suspend fun getPokemonColorByName(name: String): String {
        return dao.getPokemonByName(name)?.color ?: api.getSpecies(name).color.name
    }

    private fun String.getPicUrl(): String {
        val id = this.substringAfter("pokemon").replace("/", "")
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
    }

    private suspend fun getPokemonFromDatabase(offset: Int, limit: Int): List<Pokemon> {
        return dao.getPokemonWithPagination(limit = limit, offset = offset)
            .map { entity ->
                Log.w("Kart", "getPokemonFromDatabase: $entity ")
                entity.toDomain()
            }
    }

    private suspend fun <T, R> List<T>.asyncMap(transform: suspend (T) -> R): List<R> {
        return coroutineScope {
            this@asyncMap.map { item ->
                async { transform(item) }
            }.awaitAll()
        }
    }
}
