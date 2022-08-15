package com.vunh.login_hilt_clean.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vunh.login_hilt_clean.domain.entity.AccountEntity
import com.vunh.login_hilt_clean.data.repositories.LoginRepositoryImpl
import com.vunh.login_hilt_clean.domain.usecase.LoginUseCase
import com.vunh.login_hilt_clean.utils.UseCaseResult
import com.vunh.login_hilt_clean.utils.AppUtils.validateEmail
import com.vunh.login_hilt_clean.utils.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userManager: UserManager,
    private val loginUseCase: LoginUseCase,
    ) : ViewModel() , CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val showLoading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<String>()
    val userResult= MutableLiveData<AccountEntity>()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun getUser(username: String, password: String){
        showLoading.value = true
        launch {
            try {
                var result = withContext(Dispatchers.IO) {
                    loginUseCase.invoke(username, password)
                }
                showLoading.value = false
                when (result) {
                    is UseCaseResult.Success -> {
                        userManager.saveUser(username, password)
                        userResult.value = result.data
                    }
                    is UseCaseResult.Error -> {
                        showError.value = result.errorMessage

                    }
                }
            }catch (e: Exception) {
                showError.value = e.toString()
            }
        }
    }

    fun checkValidate(email:String,password:String):Boolean{
        if(email.isEmpty()){
            showError.value = "Email is empty"
            return false
        }
        if(!email.validateEmail()){
            showError.value = "Email incorrect type"
            return false
        }
        if(password.isEmpty()){
            showError.value = "Password less 6 charater"
            return false
        }
        return true

    }
}