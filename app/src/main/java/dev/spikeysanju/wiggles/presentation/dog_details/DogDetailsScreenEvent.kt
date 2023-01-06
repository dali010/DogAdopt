package dev.spikeysanju.wiggles.presentation.dog_details

sealed class DogDetailsScreenEvent {
    data class OnAdoptClick(val dogId: Int) : DogDetailsScreenEvent()
    data class GetDog(val dogId: Int) : DogDetailsScreenEvent()
}
