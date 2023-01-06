package dev.spikeysanju.wiggles.presentation.dog_details

import dev.spikeysanju.wiggles.model.Dog

data class DogDetailsScreenState(
    val isLoading: Boolean = false,
    val error: String = "",
    val dog: Dog? = null,
)
