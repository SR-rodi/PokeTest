package ru.sr.poketest.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.sr.poketest.domain.interactor.PokemonInteractor

class SearchVewModel(
    private val pokemonInteractor: PokemonInteractor
) : ViewModel() {

    private val state = MutableStateFlow<SearchState>(SearchState.EmptySearchInput)

    fun onChangeSearchValue(value: String) {
        state.update { state ->
            SearchState.Content(
                searchValue = value
            )
        }
    }

    fun onSearchClick(value: String) {
        if (value.isEmpty()) {
            state.update { SearchState.EmptySearchInput }
            return
        }
        viewModelScope.launch {
            state.update { SearchState.Loading }
            pokemonInteractor.getPokemonByName(name = value)
                .onSuccess { pokemon ->
                    state.update {
                        SearchState.Content(
                            PokemonDetailsVO.fromDomain(pokemon)
                        )
                    }
                }
                .onFailure { state.update { SearchState.Error } }
        }
    }
}

