package com.nyan.twtest.presentation.ui.dashboard

import androidx.lifecycle.ViewModel
import com.nyan.twtest.data.repository.local.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel
@Inject
constructor(
    private val sharedPrefs: SharedPreferencesManager
): ViewModel() {

    fun openNext() {
        sharedPrefs.name = generateRandomString()
    }

    private fun generateRandomString(): String {
        val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..20)
            .map { charset.random() }
            .joinToString("")
    }

}