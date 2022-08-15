package com.vunh.login_hilt_clean.utils

import com.vunh.login_hilt_clean.data.storage.Storage
import javax.inject.Inject
import javax.inject.Singleton

private const val LOGIN_USER = "login_user"
private const val PASSWORD = "pass_user"

@Singleton
class UserManager @Inject constructor(
    private val storage: Storage,
    ) {
    private val username: String
        get() = storage.getString(LOGIN_USER)

    fun saveUser(username: String, password: String) {
        storage.setString(LOGIN_USER, username)
        storage.setString(PASSWORD, password)
    }

    fun isUserLoggedIn() = username.isNotEmpty()

    fun logout() {
        storage.setString(LOGIN_USER, "")
        storage.setString(PASSWORD, "")
    }
}