package com.example.hotelbooking

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(
    private val repository: DataRepository
): ViewModel() {
    private val _hotelData = MutableStateFlow(Hotels())
    val hotelData: StateFlow<Hotels> = _hotelData.asStateFlow()

    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = State.Loading
            val usefulActivity = repository.getHotelData()
            _state.value = State.Success(usefulActivity)
        }
    }
    private fun loadHotelData() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.getHotelData()
            }.fold(
                onSuccess = { _hotelData.value = it },
                onFailure = { Log.d("RoomsListViewModel", it.message ?: "") }
            )
        }
    }

}