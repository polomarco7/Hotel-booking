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
class RoomViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val _roomsData = MutableStateFlow<List<Rooms>>(emptyList())
    val roomsData: StateFlow<List<Rooms>> = _roomsData.asStateFlow()

    init {
        loadRoomsData()
    }
    private fun loadRoomsData() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.getRoomsData()
            }.fold(
                onSuccess = { _roomsData.value = it },
                onFailure = { Log.d("RoomsListViewModel", it.message ?: "") }
            )
        }
    }

}