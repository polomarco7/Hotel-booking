package com.example.hotelbooking.ui.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelbooking.data.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
    private val _state = MutableStateFlow<StateBooking>(StateBooking.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = StateBooking.Loading
            val hotels = repository.getBookingData()
            _state.value = StateBooking.Success(hotels)
        }
    }
}