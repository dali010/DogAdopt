package dev.spikeysanju.wiggles.presentation.login

data class PasswordTextFieldState(
    val text: String = "",
    val isPasswordVisible: Boolean = false,
    val error: Error? = null,
    val errorText: String = ""
)
