package ru.sr.poketest.domain.interactor

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.sr.poketest.domain.model.Pokemon

interface PokemonInteractor {
    fun getPokemonPagingData(): Flow<PagingData<Pokemon>>
}
