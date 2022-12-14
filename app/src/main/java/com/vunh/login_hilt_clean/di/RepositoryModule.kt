package com.vunh.login_hilt_clean.di

import com.vunh.login_hilt_clean.domain.repositories.LoginRepository
import com.vunh.login_hilt_clean.data.repositories.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
}