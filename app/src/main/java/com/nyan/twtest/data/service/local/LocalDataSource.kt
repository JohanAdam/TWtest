package com.nyan.twtest.data.service.local

import com.nyan.twtest.data.model.HeroResp
import com.nyan.twtest.data.repository.local.SharedPreferencesManager

class LocalDataSource(
    private val sharedPreferencesManager: SharedPreferencesManager
) {
    fun getHeroList(): List<HeroResp> {
        return sharedPreferencesManager.heroList
    }
}