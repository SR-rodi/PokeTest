package ru.sr.poketest.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(pokemonList: List<PokemonEntity>)

    @Query("SELECT * FROM pokemon_table WHERE name = :name")
    suspend fun getPokemonByName(name: String): PokemonEntity?

    @Query("SELECT * FROM pokemon_table LIMIT :limit OFFSET :offset")
    suspend fun getPokemonWithPagination(offset: Int, limit: Int): List<PokemonEntity>
}
