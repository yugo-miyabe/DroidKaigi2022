package com.example.jetsurvey.signinsignup.common.email

import com.example.jetsurvey.signinsignup.common.textField.TextFieldState
import com.example.jetsurvey.signinsignup.common.textField.textFieldStateSaver
import java.util.regex.Pattern

private const val EMAIL_VALIDATION_REGEX = "^(.+)@(.+)\$"

class EmailState(val email: String? = null) :
    TextFieldState(validator = ::isEmailValid, errorFor = ::emailValidationError) {
    init {
        email?.let {
            text = it
        }
    }
}

/**
 * Returns an error to be displayed or null if no error was found
 */
private fun emailValidationError(email: String): String {
    return "Invalid email: $email"
}

private fun isEmailValid(email: String): Boolean {
    return Pattern.matches(EMAIL_VALIDATION_REGEX, email)
}

val EmailStateSaver = textFieldStateSaver(EmailState())
