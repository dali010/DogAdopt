package dev.spikeysanju.wiggles.data


import dev.spikeysanju.wiggles.domain.DogRepository
import dev.spikeysanju.wiggles.mapper.toDog
import dev.spikeysanju.wiggles.model.Dog
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogRepositoryImpl @Inject constructor(
    private val dogsLocalSource: DogLocalSource
) : DogRepository {
    override suspend fun getDogs() = dogsLocalSource.getDogs().map { it.toDog() }
    override suspend fun getDog(dogId: Int): Dog = dogsLocalSource.getDog(dogId)?.toDog()!!
    override suspend fun updateDogAdopted(dogId: Int, adopted: Boolean) = dogsLocalSource.updateDogAdopted(dogId, adopted)
}