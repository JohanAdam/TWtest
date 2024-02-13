package com.nyan.twtest.data.repository.remote

import com.nyan.twtest.data.model.details.mapToDomain
import com.nyan.twtest.data.service.remote.NetworkService
import com.nyan.twtest.domain.entity.details.DetailsEntity
import com.nyan.twtest.domain.network.ErrorHandler
import com.nyan.twtest.domain.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteRepositoryImpl(
    private val networkService: NetworkService
): RemoteRepository {

    override fun loadDetails(id: Int): Flow<DataState<DetailsEntity>> = flow {
        emit(DataState.Loading)

        try {
            val response = networkService.getDetails(id)

            emit(DataState.Success(response.mapToDomain()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Failed(ErrorHandler(e)))
        }
    }

}