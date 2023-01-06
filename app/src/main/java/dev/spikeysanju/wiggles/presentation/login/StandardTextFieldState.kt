package dev.spikeysanju.wiggles.presentation.login

data class StandardTextFieldState(
    val text: String = "",
    val error: Error? = null,
    val errorText: String = ""
)
