package com.example.jetsurvey.repository

import androidx.compose.runtime.Immutable

sealed class User {
    @Immutable
    data class LoggedInUser(val email: String) : User()
    object GuestUser : User()
    object NoUserLoggedIn : User()
}

/**
 * ログインしたユーザを保持するリポジトリ
 */
object UserRepository {
    private var _user: User = User.NoUserLoggedIn
    val user: User
        get() = _user


    /**
     * サインイン
     */
    fun signIn(email: String, password: String) {
        _user = User.LoggedInUser(email)
    }

    /**
     * サインアップ
     */
    fun signUp(email: String, password: String) {
        _user = User.LoggedInUser(email)
    }

    /**
     * ゲストユーザでサインイン
     */
    fun signInAsGuest() {
        _user = User.GuestUser
    }

    /**
     * メールにsignupが含まれている場合、falseで返す
     */
    fun isKnownUserEmail(email: String): Boolean {
        // if the email contains "sign up" we consider it unknown
        return email.contains("signup").not()
    }
}