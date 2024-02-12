package com.nyan.twtest.data.repository.local

import android.content.Context
import android.content.SharedPreferences
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
}