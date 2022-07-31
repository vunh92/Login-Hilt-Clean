package com.vunh.login_hilt_clean.viewModel

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vunh.login_hilt_clean.model.Account
import com.vunh.login_hilt_clean.repository.login.LoginRepositoryImpl
import com.vunh.login_hilt_clean.usecase.UseCaseResult
import com.vunh.login_hilt_clean.users.UserManager
import com.vunh.login_hilt_clean.utils.AppUtils.validateEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepositoryImp: LoginRepositoryImpl,
    private val userManager: UserManager,
    ) : ViewModel() , CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val showLoading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<String>()
    val userResult= MutableLiveData<Account>()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun getUser(username: String, password: String){
        showLoading.value = true
        launch {
            try {
                var result = withContext(Dispatchers.IO) {
                    loginRepositoryImp.getUser(username, password)
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