package ru.sr.poketest.domain.interactor

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.sr.poketest.domain.model.Pokemon
import ru.sr.poketest.domain.model.PokemonDetails

interface PokemonInteractor {
    fun getPokemonPagingData(color: String?): Flow<PagingData<Pokemon>>
    suspend fun getPokemonByName(name:String): Result<PokemonDetails>
}
