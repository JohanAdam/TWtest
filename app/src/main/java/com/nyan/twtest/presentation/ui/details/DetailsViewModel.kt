package com.nyan.twtest.presentation.ui.details

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
class DetailViewModel
@Inject
constructor(
    private val localRepository: LocalRepository,
): ViewModel() {

    private val _loading: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val loading: LiveData<Event<Boolean>> get() = _loading

    private val _result: MutableLiveData<Event<HeroEntity>> = MutableLiveData()
    val result: LiveData<Event<HeroEntity>> get() = _result

    private val _msg: MutableLiveData<Event<String>> = MutableLiveData()
    val msg: LiveData<Event<String>> get() = _msg

    private var id: Int? = 0

    fun getDetails(id: Int?) {
        id?.let {
            this.id = id
            localRepository.getHeroDetails(id).onEach { dataState ->
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
                        data?.let {
                            _result.value = Event(it)
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun setRating(rating: Float) {
        id?.let {
            localRepository.setRating(it, rating).onEach { dataState ->
                _loading.value = Event(false)
                when(dataState) {
                    is DataState.Loading -> {
                        _loading.value = Event(true)
                    }

                    is DataState.Failed -> {
                        _msg.value = Event(dataState.error.message ?: "")
                    }

                    is DataState.Success -> {

                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}