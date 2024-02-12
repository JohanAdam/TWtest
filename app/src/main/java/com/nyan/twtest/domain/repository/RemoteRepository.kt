package com.nyan.twtest.domain.repository

import com.nyan.twtest.domain.entity.details.DetailsEntity
import com.nyan.twtest.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {

    fun loadDetails(id: Int) : Flow<DataState<DetailsEntity>>

}