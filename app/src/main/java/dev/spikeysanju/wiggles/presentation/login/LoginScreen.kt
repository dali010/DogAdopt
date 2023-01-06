package dev.spikeysanju.wiggles.presentation.login


import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.spikeysanju.wiggles.R
import dev.spikeysanju.wiggles.component.CustomDialog
import dev.spikeysanju.wiggles.component.CustomTextField
import dev.spikeysanju.wiggles.component.DialogType
import dev.spikeysanju.wiggles.navigation.Screen
import dev.spikeysanju.wiggles.ui.theme.DarkGreen
import dev.spikeysanju.wiggles.ui.theme.LightGreen
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val state = viewModel.loginState.value
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = state) {
        if (state.loginSucceed) {
            delay(50L)
            navController.navigate(Screen.Home.route)
        }
    }

    Column(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures {
                    keyboardController?.hide()
                }
            }
            .fillMaxSize()
            .background(color = DarkGreen)
            .padding(20.dp, top = 30.dp)
    ) {
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        LoginText(modifier = Modifier.padding(horizontal = 20.dp))
        Spacer(modifier = Modifier.height(40.dp))
        CustomTextField(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp),
            text = emailState.text,
            onValueChange = {
                viewModel.onEvent(LoginEvent.EnteredEmail(it))
            },
            maxLines = 1,
            singleLine = true,
            keyboardType = KeyboardType.Email,
            hint = stringResource(id = R.string.login_hint),
        )
        Spacer(modifier = Modifier.height(15.dp))

        CustomTextField(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp),
            text = passwordState.text,
            onValueChange = {
                viewModel.onEvent(LoginEvent.EnteredPassword(it))
            },
            hint = stringResource(id = R.string.password_hint),
            keyboardType = KeyboardType.Password,

            isPasswordVisible = passwordState.isPasswordVisible,
            onPasswordToggleClick = {
                viewModel.onEvent(LoginEvent.TogglePasswordVisibility)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                viewModel.onEvent(LoginEvent.Login)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = LightGreen),
            shape = RoundedCornerShape(17.dp),
            modifier = Modifier
                .height(60.dp)
                .width(300.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.body1.copy(
                    color = DarkGreen,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
        if (state.dialogStatus) {
            CustomDialog(
                description = "Verify your credentials please",
                dialogType = DialogType.ErrorDialog.type
            ) {
                viewModel.onEvent(LoginEvent.ManageSuccessDialog(false))
            }
        }

    }



}

@Composable
fun LoginText(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.h1,
        )
        Text(
            text = "Welcome Back.",
            style = MaterialTheme.typography.h2,
        )
        Text(
            text = "You've been missed !",
            style = MaterialTheme.typography.h2,
        )
    }
}
