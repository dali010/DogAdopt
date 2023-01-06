package dev.spikeysanju.wiggles.data

import javax.inject.Inject

class DogLocalSource @Inject constructor(
    private val dogDao: DogDao
) {
    suspend fun getDogs() = dogDao.getDogs()
    suspend fun getDog(dogId: Int) = dogDao.getDog(dogId)
    suspend fun updateDogAdopted(dogId: Int,adopted: Boolean) = dogDao.updateDogAdopted(dogId, adopted)
}