package dev.spikeysanju.wiggles.presentation.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
//                dogs = repository.getDogs()
            )
        }
    }

    fun onEvent(event: DogScreenEvent) {
        when (event) {
            is DogScreenEvent.GetDogs -> Unit
        }
    }
}

class ViewModelFactory constructor(private val repository:DogRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DogRepository::class.java)) {
            HomeScreenViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}