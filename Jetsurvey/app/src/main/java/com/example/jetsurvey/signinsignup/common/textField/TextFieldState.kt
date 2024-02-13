package com.example.jetsurvey.signinsignup.common.textField

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue

open class TextFieldState(
    private val validator: (String) -> Boolean = { true },
    private val errorFor: (String) -> String = { "" }
) {
    var text: String by mutableStateOf("")
    var isFocusedDirty: Boolean by mutableStateOf(false)
    var isFocused: Boolean by mutableStateOf(false)
    private var displayErrors: Boolean by mutableStateOf(false)

    open val isValid: Boolean
        get() = validator(text)

    fun onFocusChange(focused: Boolean) {
        isFocused = focused
        if (focused) isFocusedDirty = true
    }

    fun enableShowErrors() {
        // 一度でもフォーカスされていたら、エラーを出す
        if (isFocusedDirty) {
            displayErrors = true
        }
    }

    fun showErrors() = isValid.not() && displayErrors

    open fun getError(): String? {
        return if (showErrors()) {
            errorFor(text)
        } else {
            null
        }
    }
}

/**
 * テキストの状態を保存、復元する役割
 */
fun textFieldStateSaver(state: TextFieldState) = listSaver<TextFieldState, Any>(
    save = { textFieldState ->
        listOf(
            textFieldState.text,
            textFieldState.isFocusedDirty
        )
    },
    restore = {
        state.apply {
            text = it[0] as String
            isFocusedDirty = it[1] as Boolean
        }
    }
)
