package dev.spikeysanju.wiggles.domain

import dev.spikeysanju.wiggles.data.DogEntity

interface DogRepository {
    suspend fun getDogs(): List<DogEntity>
}