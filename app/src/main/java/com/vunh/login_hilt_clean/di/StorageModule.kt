package com.vunh.login_hilt_clean.di

import com.vunh.login_hilt_clean.data.storage.SharedPreferencesStorage
import com.vunh.login_hilt_clean.data.storage.Storage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class StorageModule {
    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage
}