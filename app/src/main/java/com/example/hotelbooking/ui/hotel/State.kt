package com.example.hotelbooking.ui.hotel

import com.example.hotelbooking.models.Hotels


sealed class State {
    object Loading : State()
    data class Success(val hotels: Hotels): State()
}