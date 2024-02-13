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
    private val localDataSource: LocalDataSource,
    private val sharedPreferencesManager: SharedPreferencesManager
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
            val hero = localDataSource.getHero(id)
            emit(DataState.Success(hero?.mapToDomain()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Failed(ErrorHandler(e)))
        }
    }

    override fun setRating(id: Int, rating: Float): Flow<DataState<Unit>> = flow {
        emit(DataState.Loading)

        try {
            val heroList = localDataSource.getHeroList().toMutableList()

            val heroToUpdate = heroList.find { it.id == id }

            heroToUpdate?.let {
                it.rating = rating.toInt()
                sharedPreferencesManager.heroList = heroList
                emit(DataState.Success(Unit))
            } ?: run {
                emit(DataState.Failed(ErrorHandler(Exception("Hero with ID $id not found"))))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Failed(ErrorHandler(e)))
        }
    }

}