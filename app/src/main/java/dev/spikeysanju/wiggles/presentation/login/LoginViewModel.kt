package dev.spikeysanju.wiggles.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {

    private val _emailState = mutableStateOf(StandardTextFieldState())
    val emailState: State<StandardTextFieldState> = _emailState

    private val _passwordState = mutableStateOf(PasswordTextFieldState())
    val passwordState: State<PasswordTextFieldState> = _passwordState

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                _emailState.value = emailState.value.copy(
                    text = event.email
                )
            }
            is LoginEvent.EnteredPassword -> {
                _passwordState.value = _passwordState.value.copy(
                    text = event.password
                )
            }
            is LoginEvent.TogglePasswordVisibility -> {
                _passwordState.value = _passwordState.value.copy(
                    isPasswordVisible = !passwordState.value.isPasswordVisible
                )
            }

            is LoginEvent.Login -> {
                if (emailState.value.text == "siwar@gmail.com" && passwordState.value.text == "123456789"){
                    _loginState.value = loginState.value.copy(
                        loginSucceed = true
                    )
                }else
                    _loginState.value = loginState.value.copy(
                        dialogStatus = true
                )

            }

            is LoginEvent.ManageSuccessDialog -> {
                    _loginState.value = loginState.value.copy(
                        dialogStatus = event.dialogStatus
                    )

            }


        }
    }
}


