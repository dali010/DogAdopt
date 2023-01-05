package dev.spikeysanju.wiggles.presentation.home

import dev.spikeysanju.wiggles.data.DogEntity

data class HomeScreenState(
    val isLoading: Boolean = false,
    val error: String = "",
    val dogs: List<DogEntity>? = emptyList(),
)
