package com.nyan.twtest.presentation.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyan.twtest.domain.entity.HeroEntity
import com.nyan.twtest.domain.repository.LocalRepository
import com.nyan.twtest.domain.state.DataState
import com.nyan.twtest.presentation.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel
@Inject
constructor(
    private val localRepository: LocalRepository
): ViewModel() {

    private val _loading: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val loading: LiveData<Event<Boolean>> get() = _loading

    private val _heroList: MutableLiveData<Event<List<HeroEntity>?>> = MutableLiveData()
    val heroList: LiveData<Event<List<HeroEntity>?>> get() = _heroList

    private val _msg: MutableLiveData<Event<String>> = MutableLiveData()
    val msg: LiveData<Event<String>> get() = _msg

    fun getHeroes() {
        Log.d(TAG, "getHeroes: ")
        localRepository.loadHero().onEach { dataState ->
            _loading.value = Event(false)
            when (dataState) {
                is DataState.Failed -> {
                    _msg.value = Event(dataState.error.message ?: "")
                }
                DataState.Loading -> {
                    _loading.value = Event(true)
                }
                is DataState.Success -> {
                    Log.d(TAG, "getHeroes: ${dataState.data?.size}")
                    _heroList.value = Event(dataState.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    companion object {
        private const val TAG = "DashboardViewModel"
    }

}