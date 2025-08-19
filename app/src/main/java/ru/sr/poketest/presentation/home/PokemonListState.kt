package ru.sr.poketest.presentation.home

import androidx.paging.PagingData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class PokemonListState(
    val pagingFlow: StateFlow<PagingData<PokemonVO>> = MutableStateFlow(PagingData.Companion.empty())
)