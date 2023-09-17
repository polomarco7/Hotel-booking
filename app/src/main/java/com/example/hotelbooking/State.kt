package com.example.hotelbooking


sealed class State {
    object Loading : State()
    data class Success(val hotels: Hotels): State()
}