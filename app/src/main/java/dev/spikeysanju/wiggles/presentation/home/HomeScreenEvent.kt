package dev.spikeysanju.wiggles.presentation.home

sealed class HomeScreenEvent {
    object GetDogs : HomeScreenEvent()
    object OnAdoptClick : HomeScreenEvent()
}
