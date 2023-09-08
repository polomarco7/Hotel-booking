package com.example.hotelbooking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hotels(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "adress") val address: String,
    @Json(name = "minimal_price") val minimalPrice: Long,
    @Json(name = "price_for_it") val priceForIt:String,
    @Json(name = "rating") val rating:Int,
    @Json(name = "rating_name") val ratingName: String,
    @Json(name = "image_urls") val imageUrls: List<String>,
    @Json(name = "about_the_hotel") val aboutTheHotel: AboutTheHotel
    )

@JsonClass(generateAdapter = true)
data class AboutTheHotel(
    val description: String,
    val peculiarities: List<String>
)
