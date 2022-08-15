package com.vunh.login_hilt_clean.presentation.main

import androidx.lifecycle.ViewModel
import com.vunh.login_hilt_clean.data.repositories.LoginRepositoryImpl
import com.vunh.login_hilt_clean.utils.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loginRepositoryImp: LoginRepositoryImpl,
    private val userManager: UserManager
) : ViewModel() , CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun logout() {
        userManager.logout()
    }
}