package dev.spikeysanju.wiggles.domain

import dev.spikeysanju.wiggles.model.Dog

interface DogRepository {
    suspend fun getDogs(): List<Dog>
    suspend fun getDog(dogId: Int): Dog
    suspend fun updateDogAdopted(dogId: Int,adopted: Boolean)
}