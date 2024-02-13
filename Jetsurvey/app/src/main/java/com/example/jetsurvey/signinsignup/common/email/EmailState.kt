package com.example.jetsurvey.signinsignup.common.email

import com.example.jetsurvey.signinsignup.common.textField.TextFieldState
import com.example.jetsurvey.signinsignup.common.textField.textFieldStateSaver
import java.util.regex.Pattern


/**
 * Emailの状態保持プロパティ
 */
val EmailStateSaver = textFieldStateSaver(EmailState())

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
 * エラーがでた場合、エラーの文字列を返す
 */
private fun emailValidationError(email: String): String {
    return "Invalid email: $email"
}

/**
 * Emailが有効な形式か判定
 */
private fun isEmailValid(email: String): Boolean {
    return Pattern.matches(EMAIL_VALIDATION_REGEX, email)
}

