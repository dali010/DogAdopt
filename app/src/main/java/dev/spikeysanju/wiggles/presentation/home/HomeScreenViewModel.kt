package dev.spikeysanju.wiggles.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.spikeysanju.wiggles.domain.DogRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: DogRepository
) : ViewModel() {

    val state: MutableState<HomeScreenState> = mutableStateOf(HomeScreenState())

    init {
        runBlocking {
            state.value = state.value.copy(
                dogs = repository.getDogs()
            )
        }
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.GetDogs -> Unit
        }
    }
}
