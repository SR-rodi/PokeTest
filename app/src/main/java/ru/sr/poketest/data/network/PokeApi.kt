package ru.sr.poketest.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.sr.poketest.data.network.model.PokemonListResponseNO
import ru.sr.poketest.data.network.model.PokemonSpeciesNO

interface PokeApi {

    @GET("v2/pokemon?offset=20&limit=20")
    suspend fun getAllPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): PokemonListResponseNO

    @GET("v2/pokemon-species/{name}")
    suspend fun getSpecies(
        @Path("name") name: String
    ): PokemonSpeciesNO
}