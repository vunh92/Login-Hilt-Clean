package com.vunh.login_hilt_clean.domain.repositories

import com.vunh.login_hilt_clean.domain.entity.AccountEntity
import com.vunh.login_hilt_clean.utils.UseCaseResult


interface LoginRepository {
    suspend fun getUser(username: String, password: String): UseCaseResult<AccountEntity>
}