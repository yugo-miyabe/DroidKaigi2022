package com.example.jetsurvey.signinsignup.signin

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetsurvey.R
import com.example.jetsurvey.signinsignup.common.SignInSignUpTopAppBar
import com.example.jetsurvey.signinsignup.common.email.Email
import com.example.jetsurvey.signinsignup.common.email.EmailState
import com.example.jetsurvey.signinsignup.common.email.EmailStateSaver
import com.example.jetsurvey.signinsignup.common.password.Password
import com.example.jetsurvey.signinsignup.common.password.PasswordState
import com.example.jetsurvey.ui.theme.JetsurveyTheme
import com.example.jetsurvey.util.supportWideScreen
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    email: String?,
    onSignInSubmitted: (email: String, password: String) -> Unit,
    onSignInAsGuest: () -> Unit,
    onNavUp: () -> Unit,
) {
    // コルーチンスコープ
    val scope = rememberCoroutineScope()

    val snackBarHostState = remember { SnackbarHostState() }
    val snackBarErrorText = stringResource(id = R.string.feature_not_available)
    val snackBarActionLabel = stringResource(id = R.string.dismiss)

    Scaffold(
        topBar = {
            SignInSignUpTopAppBar(
                topAppBarText = stringResource(id = R.string.sign_in),
                onNavUp = onNavUp,
            )
        },
        content = { contentPadding ->
            SignInSignUpScreen(
                onSignInAsGuest = onSignInAsGuest,
                modifier = Modifier.supportWideScreen(),
                contentPadding = contentPadding,
                content = {
                    // サインアップ
                    Column(modifier = Modifier.fillMaxWidth()) {
                        SignInContent(
                            email = email,
                            onSignUpSubmitted = onSignInSubmitted
                        )
                    }
                    // 16dpのスペース
                    Spacer(modifier = Modifier.height(16.dp))
                    // forgot passwordボタン
                    TextButton(
                        onClick = {
                            scope.launch {
                                snackBarHostState.showSnackbar(
                                    message = snackBarErrorText, actionLabel = snackBarActionLabel
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(id = R.string.forgot_password))
                    }
                }
            )
        }
    )

    // スナックバーを画面幅まで広げる
    Box(modifier = Modifier.fillMaxSize()) {
        ErrorSnackBar(
            snackBarHostState = snackBarHostState,
            onDismiss = { snackBarHostState.currentSnackbarData?.dismiss() },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun SignInContent(
    email: String?,
    onSignInSubmitted: (email: String, password: String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val focusRequester = remember { FocusRequester() }
        val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
            mutableStateOf(EmailState(email))
        }
        Email(emailState, onImeAction = { focusRequester.requestFocus() })

        Spacer(modifier = Modifier.height(16.dp))

        val passwordState = remember { PasswordState() }

        val onSubmit = {
            if (emailState.isValid && passwordState.isValid) {
                onSignInSubmitted(emailState.text, passwordState.text)
            }
        }
        Password(
            label = stringResource(id = R.string.password),
            passwordState = passwordState,
            modifier = Modifier.focusRequester(focusRequester),
            onImeAction = { onSubmit() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onSubmit() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            enabled = emailState.isValid && passwordState.isValid
        ) {
            Text(
                text = stringResource(id = R.string.sign_in)
            )
        }
    }
}

@Composable
fun ErrorSnackBar(
    snackBarHostState: SnackbarHostState, modifier: Modifier = Modifier, onDismiss: () -> Unit = { }
) {
    SnackbarHost(
        hostState = snackBarHostState, snackbar = { data ->
            Snackbar(modifier = Modifier.padding(16.dp), content = {
                Text(
                    text = data.visuals.message,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }, action = {
                data.visuals.actionLabel?.let {
                    TextButton(onClick = onDismiss) {
                        Text(
                            text = stringResource(id = R.string.dismiss),
                            color = MaterialTheme.colorScheme.inversePrimary
                        )
                    }
                }
            })
        }, modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom)
    )
}

@Composable
fun SignUpContent(
    email: String?,
    onSignUpSubmitted: (email: String, password: String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val passwordFocusRequest = remember { FocusRequester() }
        val confirmationPasswordFocusRequest = remember { FocusRequester() }
        val emailState = remember { EmailState(email) }
        val passwordState = remember { PasswordState() }
        Email(emailState, onImeAction = {
            passwordFocusRequest.requestFocus()
        })

        val onSubmit = {
            if (emailState.isValid && passwordState.isValid) {
                onSignUpSubmitted(emailState.text, passwordState.text)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Password(
            label = stringResource(id = R.string.password),
            passwordState = remember { PasswordState() },
            modifier = Modifier,
            onImeAction = { }
        )

    }
}


@Preview(name = "Sign in light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Sign in dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SignInPreview() {
    JetsurveyTheme {
        SignInScreen(
            email = null,
            onSignInSubmitted = { _, _ -> },
            onSignInAsGuest = {},
            onNavUp = {},
        )
    }
}