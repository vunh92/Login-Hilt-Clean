package com.vunh.login_hilt_clean.di

import com.vunh.login_hilt_clean.api.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideLoginService( retrofit: Retrofit)
            : LoginService = retrofit.create(LoginService::class.java)
}