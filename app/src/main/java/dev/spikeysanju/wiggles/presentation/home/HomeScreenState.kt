package dev.spikeysanju.wiggles.presentation.home

import dev.spikeysanju.wiggles.model.Dog

data class HomeScreenState(
    val isLoading: Boolean = false,
    val error: String = "",
    val dogs: List<Dog>? = emptyList(),
)
