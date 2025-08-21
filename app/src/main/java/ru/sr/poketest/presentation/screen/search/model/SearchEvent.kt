package ru.sr.poketest.presentation.screen.search.model

sealed interface SearchEvent {
    object NavigateToPopUp : SearchEvent
}