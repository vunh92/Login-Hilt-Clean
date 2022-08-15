package com.vunh.login_hilt_clean.domain.usecase

import com.vunh.login_hilt_clean.domain.entity.AccountEntity
import com.vunh.login_hilt_clean.domain.repositories.LoginRepository
import com.vunh.login_hilt_clean.utils.UseCaseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend fun invoke(username: String, password: String): UseCaseResult<AccountEntity> {
        return loginRepository.getUser(username, password)
    }
}
