package com.vunh.login_hilt_clean.data.repositories

import com.vunh.login_hilt_clean.api.LoginService
import com.vunh.login_hilt_clean.domain.entity.AccountEntity
import com.vunh.login_hilt_clean.domain.repositories.LoginRepository
import com.vunh.login_hilt_clean.utils.UseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginService: LoginService) :
    LoginRepository {
    override suspend fun getUser(username: String, password: String): UseCaseResult<AccountEntity> {
        return try {
            val result =
                withContext(Dispatchers.IO) { loginService.callLoginAsync(username, password).await() }
            UseCaseResult.Success(result)
        } catch (ex: Throwable) {
            UseCaseResult.Error(ex.message ?: "")
        }
    }
}