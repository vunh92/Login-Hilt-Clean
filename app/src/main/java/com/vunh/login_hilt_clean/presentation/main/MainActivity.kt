package com.vunh.login_hilt_clean.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.vunh.login_hilt_clean.databinding.ActivityMainBinding
import com.vunh.login_hilt_clean.presentation.login.LoginActivity
import com.vunh.login_hilt_clean.utils.UserManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    @Inject
    lateinit var userManager: UserManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!userManager.isUserLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }else {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
//            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
            initializeView()
            initializeViewModel()
        }

    }

    private fun initializeView() {
        binding.logoutBtn.setOnClickListener {
            viewModel.logout()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    private fun initializeViewModel() {}

}