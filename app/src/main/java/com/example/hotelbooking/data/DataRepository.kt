package com.example.hotelbooking.data

import com.example.hotelbooking.models.BookingModel
import com.example.hotelbooking.models.Hotels
import com.example.hotelbooking.models.Rooms
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