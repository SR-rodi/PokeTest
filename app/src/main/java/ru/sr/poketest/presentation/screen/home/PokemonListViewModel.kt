package ru.sr.poketest.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.sr.poketest.domain.interactor.PokemonInteractor
import ru.sr.poketest.domain.model.PokemonColor
import ru.sr.poketest.presentation.screen.home.model.PokemonListEvent
import ru.sr.poketest.presentation.screen.home.model.PokemonListState
import ru.sr.poketest.presentation.screen.home.model.PokemonListViewAction
import ru.sr.poketest.presentation.screen.home.model.PokemonVO

class PokemonListViewModel(
    private val pokemonInteractor: PokemonInteractor
) : ViewModel() {

    private val pokemonState = MutableStateFlow(PokemonListState())
    val viewState = pokemonState.asStateFlow()

    private val event = MutableSharedFlow<PokemonListEvent?>()
    val viewEvent = event.asSharedFlow()

    init {
        loadAllPokemon(null)
    }

    fun onActionHandle(action: PokemonListViewAction) {
        when (action) {
            is PokemonListViewAction.OnChangeSelectedColor -> {
                onChangeSelectedColor(action.color)
            }

            PokemonListViewAction.OnSearchButtonClick -> {
                onSearchButtonClick()
            }
        }
    }

    private fun onSearchButtonClick() {
        viewModelScope.launch {
            event.emit(PokemonListEvent.OnNavigateToSearchScreen)
        }
    }

    private fun onChangeSelectedColor(color: PokemonColor?) {
        pokemonState.update { state ->
            state.copy(selectPokemonColor = color)
        }
        loadAllPokemon(color = color?.colorName)
    }


    private fun loadAllPokemon(color: String?) {
        val flow: Flow<PagingData<PokemonVO>> = pokemonInteractor
            .getPokemonPagingData(color)
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

