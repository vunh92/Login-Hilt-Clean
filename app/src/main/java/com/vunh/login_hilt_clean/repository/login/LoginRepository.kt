package com.vunh.login_hilt_clean.repository.login

import com.vunh.login_hilt_clean.model.Account
import com.vunh.login_hilt_clean.usecase.UseCaseResult


interface LoginRepository {
    suspend fun getUser(username: String, password: String): UseCaseResult<Account>
}