package com.example.hotelbooking

import javax.inject.Inject
class DataRepository @Inject constructor(
    private val dataApi: DataApi
) {
    suspend fun getHotelData(): Hotels {
        return dataApi.getHotels()
    }
    suspend fun getRoomsData(): List<Rooms> {
        return dataApi.getRoomList().rooms
    }
    suspend fun getBookingData(): BookingModel {
        return dataApi.getBookingData()
    }
}