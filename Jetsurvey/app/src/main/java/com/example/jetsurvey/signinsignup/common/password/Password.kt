package com.example.jetsurvey.signinsignup.common.password

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetsurvey.R
import com.example.jetsurvey.signinsignup.common.textField.TextFieldState
import com.example.jetsurvey.ui.theme.JetsurveyTheme

@Composable
fun Password(
    label: String,
    passwordState: TextFieldState,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {}
) {
    val showPassword = rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(value = passwordState.text, onValueChange = {
        passwordState.text = it
        passwordState.enableShowErrors()
    }, modifier = modifier
        .fillMaxWidth()
        .onFocusChanged { focusState ->
            passwordState.onFocusChange(focusState.isFocused)
            if (focusState.isFocused.not()) {
                passwordState.enableShowErrors()
            }
        }, textStyle = MaterialTheme.typography.bodyMedium, label = {
        Text(
            text = label, style = MaterialTheme.typography.bodyMedium
        )
    }, trailingIcon = {
        if (showPassword.value) {
            IconButton(onClick = {
                showPassword.value = false
            }) {
                Icon(
                    imageVector = Icons.Filled.Visibility,
                    contentDescription = stringResource(id = R.string.hide_password)
                )
            }
        } else {
            IconButton(onClick = {
                showPassword.value = true
            }) {
                Icon(
                    imageVector = Icons.Filled.VisibilityOff,
                    contentDescription = stringResource(id = R.string.show_password)
                )
            }
        }
    })
}


@Preview
@Composable
private fun PasswordPreview() {
    JetsurveyTheme {
        Surface {
            Password(label = stringResource(id = R.string.password),
                passwordState = remember { PasswordState() },
                modifier = Modifier,
                onImeAction = { })
        }
    }
}


