package com.nyan.twtest.domain.repository

import com.nyan.twtest.domain.entity.HeroEntity
import com.nyan.twtest.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    fun loadHero(): Flow<DataState<List<HeroEntity>?>>
    fun getHeroDetails(id: Int): Flow<DataState<HeroEntity?>>

}