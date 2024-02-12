package com.nyan.twtest.di

import com.nyan.twtest.data.repository.remote.RemoteRepositoryImpl
import com.nyan.twtest.data.service.NetworkService
import com.nyan.twtest.domain.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideRemoteRepository(networkService: NetworkService): RemoteRepository {
        return RemoteRepositoryImpl(networkService)
    }

}