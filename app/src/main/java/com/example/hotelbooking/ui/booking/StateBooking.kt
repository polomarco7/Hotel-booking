package com.example.hotelbooking.ui.booking

import com.example.hotelbooking.models.BookingModel

sealed class StateBooking {
    object Loading : StateBooking()
    data class Success(val rooms: BookingModel): StateBooking()
}