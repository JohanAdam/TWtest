package com.nyan.twtest.di

import com.nyan.twtest.data.repository.local.LocalRepositoryImpl
import com.nyan.twtest.data.repository.local.SharedPreferencesManager
import com.nyan.twtest.data.service.local.LocalDataSource
import com.nyan.twtest.domain.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideLocalRepository(dataSource: LocalDataSource, sharedPreferencesManager: SharedPreferencesManager): LocalRepository {
        return LocalRepositoryImpl(dataSource, sharedPreferencesManager)
    }

}