package com.nyan.twtest.data.repository.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nyan.twtest.R
import com.nyan.twtest.data.model.HeroResp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesManager
@Inject
constructor(
    @ApplicationContext val context: Context
) {

    companion object {
        const val SHARED_PREFS = "test"

        const val USER_NAME = "user_name"
        const val DUMMY_DATASET = "dummy_dataset"
    }

    private val sharedPrefs: SharedPreferences
        get() = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

    private val editor: SharedPreferences.Editor get() = sharedPrefs.edit()

    var name: String
        get() {
            return sharedPrefs.getString(USER_NAME, "") ?: ""
        }
        set(value) {
            editor.putString(USER_NAME, value).apply()
        }

//    var dummyDataset: String
//        get() = sharedPrefs.getString(DUMMY_DATASET, "") ?: ""
//        set(value) {
//            editor.putString(DUMMY_DATASET, value)
//        }


    private val gson = Gson()
    private val type = object : TypeToken<List<HeroResp>>() {}.type
    var heroList: List<HeroResp>
        get() {
            val json = sharedPrefs.getString(DUMMY_DATASET, null)
            return if (json != null) {
                gson.fromJson(json, type)
            } else {
                createHeroDummyResponses()
            }
        }
        set(value) {
            val json = gson.toJson(value)
            editor.putString(DUMMY_DATASET, json).apply()
        }

    private fun createHeroDummyResponses(): List<HeroResp> {
        val hero1 = HeroResp(1, "Superman", R.drawable.superman, 0)
        val hero2 = HeroResp(2, "Ironman", R.drawable.avenger_ironman, 0)
        val hero3 = HeroResp(3, "Hulk", R.drawable.avenger_hulk, 0)

        return listOf(hero1, hero2, hero3)
    }
}