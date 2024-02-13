package com.nyan.twtest.data.repository.local

import com.nyan.twtest.data.model.mapToDomain
import com.nyan.twtest.data.service.local.LocalDataSource
import com.nyan.twtest.domain.entity.HeroEntity
import com.nyan.twtest.domain.network.ErrorHandler
import com.nyan.twtest.domain.repository.LocalRepository
import com.nyan.twtest.domain.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalRepositoryImpl(
    private val localDataSource: LocalDataSource
): LocalRepository {

    override fun loadHero(): Flow<DataState<List<HeroEntity>?>> = flow {
        emit(DataState.Loading)

        try {
            val heroList = localDataSource.getHeroList()

            val heroListEntity = heroList.map {
                it.mapToDomain()
            }

            emit(DataState.Success(heroListEntity))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Failed(ErrorHandler(e)))
        }
    }

    override fun getHeroDetails(id: Int): Flow<DataState<HeroEntity?>> = flow {
        emit(DataState.Loading)

        try {
            val heroList = localDataSource.getHeroList()

            val heroEntity = heroList.map {
                it.mapToDomain()
            }.find { it.id == id }

            emit(DataState.Success(heroEntity))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Failed(ErrorHandler(e)))
        }
    }

}