package com.example.jetsurvey.signinsignup.signin

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetsurvey.signinsignup.signin.signInSignUp.SignInSignUpScreen
import com.example.jetsurvey.ui.theme.JetsurveyTheme
import com.example.jetsurvey.util.supportWideScreen

@Composable
fun SignInScreen(
    email: String?,
    onSignInSubmitted: (email: String, password: String) -> Unit,
    onSignInAsGuest: () -> Unit,
    onNavUp: () -> Unit,
) {
    Scaffold(topBar = {}, content = { contentPadding ->
        SignInSignUpScreen(
            onSignInAsGuest = onSignInAsGuest,
            modifier = Modifier.supportWideScreen(),
            contentPadding = contentPadding,
        ) {
            Column {

            }
        }
    })
}

@Composable
fun SignUpContent(
    email: String?,
    onSignUpSubmitted: (email: String, password: String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val passwordFocusRequest = remember { FocusRequester() }
        val confirmationPasswordFocusRequest = remember { FocusRequester() }
    }
}


@Preview(name = "Sign in light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Sign in dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SignInPreview() {
    JetsurveyTheme {

    }
}