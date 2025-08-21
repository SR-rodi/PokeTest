package ru.sr.poketest.presentation.screen.search.model

data class SearchViewState(
    val searchValue: String = "",
    val searchedState: SearchedState = SearchedState.EmptySearchInput
)