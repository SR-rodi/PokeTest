package ru.sr.poketest.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.sr.poketest.domain.interactor.PokemonInteractor
import ru.sr.poketest.presentation.screen.search.model.PokemonDetailsVO
import ru.sr.poketest.presentation.screen.search.model.SearchEvent
import ru.sr.poketest.presentation.screen.search.model.SearchViewAction
import ru.sr.poketest.presentation.screen.search.model.SearchViewState
import ru.sr.poketest.presentation.screen.search.model.SearchedState

class SearchVewModel(
    private val pokemonInteractor: PokemonInteractor
) : ViewModel() {

    private val state = MutableStateFlow(SearchViewState())
    val viewState = state.asStateFlow()

    private val event = MutableSharedFlow<SearchEvent?>()
    val viewEvent = event.asSharedFlow()

    fun onActionHandle(action: SearchViewAction) {
        when (action) {
            SearchViewAction.OnBackArrowClick -> setBackNavigationEvent()
            is SearchViewAction.OnChangeSearchValue -> onChangeSearchValue(action.value)
            SearchViewAction.OnSearchButtonClick -> {
                onSearchClick(state.value.searchValue)
            }
        }
    }

    private fun setBackNavigationEvent() {
        viewModelScope.launch {
            event.emit(SearchEvent.NavigateToPopUp)
        }
    }

    private fun onChangeSearchValue(value: String) {
        state.update { state ->
            state.copy(searchValue = value)
        }
    }

    private fun onSearchClick(value: String) {
        if (value.isEmpty()) {
            state.update { state ->
                state.copy(searchedState = SearchedState.EmptySearchInput)
            }
            return
        }
        viewModelScope.launch {
            state.update { state ->
                state.copy(searchedState = SearchedState.Loading)
            }
            pokemonInteractor.getPokemonByName(name = value)
                .onSuccess { pokemon ->
                    state.update { state ->
                        state.copy(
                            searchedState = SearchedState.Content(
                                PokemonDetailsVO.fromDomain(pokemon)
                            )
                        )
                    }
                }
                .onFailure {
                    state.update { state ->
                        state.copy(searchedState = SearchedState.Error)
                    }
                }
        }
    }
}

