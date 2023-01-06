package dev.spikeysanju.wiggles.presentation.dog_details

sealed class DogDetailsScreenEvent {
    object OnAdoptClick : DogDetailsScreenEvent()
    data class GetDog(val dogId: Int) : DogDetailsScreenEvent()
}
