package dev.spikeysanju.wiggles.presentation.login


data class LoginState(
    val isLoading: Boolean = false,
    val loginSucceed: Boolean = false,
    val error: String = "",
    val dialogStatus: Boolean = false
)
