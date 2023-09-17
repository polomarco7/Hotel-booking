package com.example.hotelbooking

sealed class StateRooms {
    object Loading : StateRooms()
    data class Success(val rooms: List<Rooms>): StateRooms()
}