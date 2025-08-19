package ru.sr.poketest.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ru.sr.poketest.domain.interactor.PokemonInteractor

class HomeViewModel(
    private val pokemonInteractor: PokemonInteractor
) : ViewModel() {

    val pokemonState = MutableStateFlow(PokemonListState())
    val viewState = pokemonState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        val flow: Flow<PagingData<PokemonVO>> = pokemonInteractor
            .getPokemonPagingData()
            .map { pagingData ->
                pagingData.map { pokemon ->
                    PokemonVO.fromDomain(pokemon)
                }
            }
            .cachedIn(viewModelScope)

        pokemonState.update { state ->
            state.copy(
                pagingFlow = flow.stateIn(
                    viewModelScope,
                    started = SharingStarted.Lazily,
                    initialValue = PagingData.empty()
                )

            )
        }
    }
}

