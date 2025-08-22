package ru.sr.poketest.presentation.screen.home.model

import androidx.paging.PagingData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.sr.poketest.domain.model.PokemonColor

data class PokemonListState(
    val pagingFlow: StateFlow<PagingData<PokemonVO>> = MutableStateFlow(PagingData.Companion.empty()),

    val selectPokemonColor: PokemonColor? = null
)
