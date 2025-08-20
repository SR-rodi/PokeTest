package ru.sr.poketest.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.sr.poketest.domain.repository.PokemonRepository
import ru.sr.poketest.domain.model.Pokemon

class PokemonPagingSource(
    private val repository: PokemonRepository,
    private val pageSize: Int = 10
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val closestPage = state.closestPageToPosition(anchorPosition)
            closestPage?.prevKey?.plus(pageSize) ?: closestPage?.nextKey?.minus(pageSize)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val offset = params.key ?: 0
        return repository.getPokemon(offset, pageSize)
            .fold(
                onSuccess = { pokemon ->
                    LoadResult.Page(
                        data = pokemon,
                        prevKey = if (offset == 0) null else offset - pageSize,
                        nextKey = if (pokemon.isEmpty()) null else offset + pageSize
                    )
                },
                onFailure = { errorException ->
                    LoadResult.Error(errorException)
                }
            )
    }
}