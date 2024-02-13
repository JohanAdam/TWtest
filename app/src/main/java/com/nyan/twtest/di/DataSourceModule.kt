package com.nyan.twtest.di

import com.nyan.twtest.data.repository.local.SharedPreferencesManager
import com.nyan.twtest.data.service.local.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideLocalDataSource(sharedPreferencesManager: SharedPreferencesManager): LocalDataSource {
        return LocalDataSource(sharedPreferencesManager)
    }

}