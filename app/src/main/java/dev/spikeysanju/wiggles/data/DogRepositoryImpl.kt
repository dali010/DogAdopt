package dev.spikeysanju.wiggles.data


import dev.spikeysanju.wiggles.domain.DogRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogRepositoryImpl @Inject constructor(
    private val dogsLocalSource: DogLocalSource
) : DogRepository {
    override suspend fun getDogs() = dogsLocalSource.getDogs()
}