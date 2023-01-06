package dev.spikeysanju.wiggles.domain

import dev.spikeysanju.wiggles.model.Dog

interface DogRepository {
    suspend fun getDogs(): List<Dog>
    suspend fun getDog(dogId: Int): Dog
}