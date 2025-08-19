package ru.sr.poketest.domain.interactor.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.sr.poketest.data.pagingSource.PokemonPagingSource
import ru.sr.poketest.domain.interactor.PokemonInteractor
import ru.sr.poketest.domain.model.Pokemon
import ru.sr.poketest.domain.repository.PokemonRepository

class PokemonInteractorImpl(
    private val repository: PokemonRepository
) : PokemonInteractor {
    override fun getPokemonPagingData(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE * 2,
                prefetchDistance = 5
            ),
            pagingSourceFactory = {
                PokemonPagingSource(
                    repository,
                    PAGE_SIZE
                )
            }
        ).flow
    }

    private companion object{
        const val PAGE_SIZE = 10
    }
}