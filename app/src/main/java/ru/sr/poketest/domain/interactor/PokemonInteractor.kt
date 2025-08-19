package ru.sr.poketest.domain.interactor

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.sr.poketest.domain.model.Pokemon

interface PokemonInteractor {
    fun getPokemonPagingData(pageSize: Int = 20): Flow<PagingData<Pokemon>>
}

