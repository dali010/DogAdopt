package dev.spikeysanju.wiggles.presentation.login

sealed class LoginEvent {
    data class EnteredEmail(val email: String) : LoginEvent()
    data class EnteredPassword(val password: String) : LoginEvent()
    object Login : LoginEvent()
    object DismissDialog : LoginEvent()
    object TogglePasswordVisibility : LoginEvent()
    object DismissNetworkNotAvailableDialog : LoginEvent()

}

