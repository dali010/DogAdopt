package dev.spikeysanju.wiggles.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.spikeysanju.wiggles.ui.theme.MediumGray

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String? = "",
    hint: String = "",
    error: String = "",
    maxLines: Int = 1,
    singleLine: Boolean = false,
    enabled: Boolean = true,
    color: Color = White,
    borderColor: Color = White,
    readOnly: Boolean = false,
    backgroundColor: Color = Transparent,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordToggleDisplayed: Boolean = keyboardType == KeyboardType.Password,
    isPasswordVisible: Boolean = false,
    onPasswordToggleClick: (Boolean) -> Unit = {},
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        OutlinedTextField(
            value = text!!,
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.body2.copy(
                        color = MediumGray.copy(
                            alpha = .7F
                        )
                    )
                )
            },
            maxLines = 1,
            singleLine = true,
            onValueChange = {
                onValueChange(it)
            },
            enabled = enabled,
            textStyle = MaterialTheme.typography.body2.copy(
                color = color
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
                backgroundColor = backgroundColor
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            visualTransformation = if (!isPasswordVisible && isPasswordToggleDisplayed) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            readOnly = readOnly,
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                },
                onDone = { keyboardController?.hide() }
            ),
            shape = RoundedCornerShape(12.dp),
            trailingIcon = if (isPasswordToggleDisplayed) {
                val icon: @Composable () -> Unit = {
                    IconButton(
                        onClick = {
                            onPasswordToggleClick(!isPasswordVisible)
                        }
                    ) {
                        if (isPasswordVisible) {
                            Image(
                                painter = painterResource(id = dev.spikeysanju.wiggles.R.drawable.ic_password_visible_eye),
                                contentDescription = null
                            )
                        } else {
                            Image(
                                painter = painterResource(id = dev.spikeysanju.wiggles.R.drawable.ic_password_invisible_eye),
                                contentDescription = null
                            )
                        }
                    }
                }
                icon
            } else null,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        )
        if (error.isNotEmpty()) {
            Text(
                text = error,
                style = MaterialTheme.typography.body2.copy(
                    fontSize = 11.sp
                ),
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp)
            )
        }
    }
}
