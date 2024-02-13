package com.example.jetsurvey.signinsignup.signin.signInSignUp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetsurvey.ui.theme.JetsurveyTheme

@Composable
fun SignInSignUpScreen(
    onSignInAsGuest: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    content: @Composable () -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        item {

        }
    }
}
@Preview
@Composable
fun SignInSignUpScreenPreview() {
    JetsurveyTheme {
        Surface {
            SignInSignUpScreen(
                onSignInAsGuest = {},
                content = {}
            )
        }
    }
}