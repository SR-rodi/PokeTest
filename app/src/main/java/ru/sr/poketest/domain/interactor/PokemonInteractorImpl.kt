package ru.sr.poketest.domain.interactor

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.sr.poketest.data.pagingSource.PokemonPagingSource
import ru.sr.poketest.domain.model.Pokemon
import ru.sr.poketest.domain.repository.PokemonRepository

class PokemonInteractorImpl(
    private val repository: PokemonRepository
) : PokemonInteractor {
    override fun getPokemonPagingData(pageSize: Int): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false,
                initialLoadSize = pageSize * 2,
                prefetchDistance = 5
            ),
            pagingSourceFactory = {
                PokemonPagingSource(
                    repository,
                    pageSize
                )
            }
        ).flow
    }
}