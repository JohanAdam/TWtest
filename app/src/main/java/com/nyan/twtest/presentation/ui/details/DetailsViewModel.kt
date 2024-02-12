package com.nyan.twtest.presentation.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyan.twtest.data.repository.local.SharedPreferencesManager
import com.nyan.twtest.domain.entity.details.DetailsEntity
import com.nyan.twtest.domain.repository.RemoteRepository
import com.nyan.twtest.domain.state.DataState
import com.nyan.twtest.presentation.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject
constructor(
    val sharedPrefs: SharedPreferencesManager,
    val repository: RemoteRepository
): ViewModel() {

    private val _loading: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val loading: LiveData<Event<Boolean>> get() = _loading

    private val _result: MutableLiveData<Event<DetailsEntity>> = MutableLiveData()
    val result: LiveData<Event<DetailsEntity>> get() = _result

    private val _msg: MutableLiveData<Event<String>> = MutableLiveData()
    val msg: LiveData<Event<String>> get() = _msg

    fun getDetails(id: Int) {
        repository.loadDetails(id).onEach { dataState ->
            _loading.value = Event(false)
            when(dataState) {
                is DataState.Loading -> {
                    _loading.value = Event(true)
                }
                is DataState.Failed -> {
                    _msg.value = Event(dataState.error.message ?: "")
                }
                is DataState.Success -> {
                    val data = dataState.data
                    _result.value = Event(data)
                }
            }
        }.launchIn(viewModelScope)
    }

}