package com.example.hotelbooking.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookingModel(
    @Json(name = "id") val id: String,
    @Json(name = "hotel_name") val hotelName: String,
    @Json(name = "hotel_adress") val hotelAddress: String,
    @Json(name = "horating") val horating: Int,
    @Json(name = "rating_name") val ratingName: String,
    @Json(name = "departure") val departure: String,
    @Json(name = "arrival_country") val arrivalCountry: String,
    @Json(name = "tour_date_start") val tourDateStart: String,
    @Json(name = "tour_date_stop") val tourDateStop: String,
    @Json(name = "number_of_nights") val numberOfNights: Int,
    @Json(name = "room") val room: String,
    @Json(name = "nutrition") val nutrition: String,
    @Json(name = "tour_price") val tourPrice: Long,
    @Json(name = "fuel_charge") val fuelCharge: Long,
    @Json(name = "service_charge") val serviceCharge: Long
    )
