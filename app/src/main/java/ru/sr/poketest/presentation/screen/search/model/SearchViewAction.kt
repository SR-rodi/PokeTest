package ru.sr.poketest.presentation.screen.search.model

sealed interface SearchViewAction {
    object OnBackArrowClick : SearchViewAction
    class OnChangeSearchValue(val value: String) : SearchViewAction
    object OnSearchButtonClick : SearchViewAction
}
