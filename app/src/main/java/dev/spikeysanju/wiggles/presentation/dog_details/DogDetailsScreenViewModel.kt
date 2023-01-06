package dev.spikeysanju.wiggles.presentation.dog_details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.spikeysanju.wiggles.domain.DogRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class DogDetailsScreenViewModel @Inject constructor(
    private val repository: DogRepository
) : ViewModel() {

    val state: MutableState<DogDetailsScreenState> = mutableStateOf(DogDetailsScreenState())


    fun onEvent(event: DogDetailsScreenEvent) {
        when (event) {
            is DogDetailsScreenEvent.OnAdoptClick -> {

            }

            is DogDetailsScreenEvent.GetDog -> {
                runBlocking {
                    state.value = state.value.copy(
                        dog = repository.getDog(event.dogId)
                    )
                }
            }
        }
    }
}
