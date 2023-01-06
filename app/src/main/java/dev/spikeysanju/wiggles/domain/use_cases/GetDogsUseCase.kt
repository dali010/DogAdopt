package dev.spikeysanju.wiggles.domain.use_cases

import dev.spikeysanju.wiggles.data.DogEntity
import dev.spikeysanju.wiggles.domain.DogRepository
import dev.spikeysanju.wiggles.model.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetDogsUseCase @Inject constructor(
    private val repository: DogRepository
) {
    operator fun invoke(): Flow<List<Dog>> {
        return flow {
            val dogsResponse = repository.getDogs()
            emit(dogsResponse)
        }
    }
}