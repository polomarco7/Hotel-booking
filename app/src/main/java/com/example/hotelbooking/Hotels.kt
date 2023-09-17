package com.example.hotelbooking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hotels(
    @Json(name = "id") val id: Int = 0,
    @Json(name = "name") val name: String? = null,
    @Json(name = "adress") val address: String? = null,
    @Json(name = "minimal_price") val minimalPrice: Long = 0,
    @Json(name = "price_for_it") val priceForIt:String? = null,
    @Json(name = "rating") val rating:Int = 0,
    @Json(name = "rating_name") val ratingName: String? = null,
    @Json(name = "image_urls") val imageUrls: List<String> = emptyList(),
    @Json(name = "about_the_hotel") val aboutTheHotel: AboutTheHotel? = null
    )

@JsonClass(generateAdapter = true)
data class AboutTheHotel(
    val description: String? = null,
    val peculiarities: List<String> = emptyList()
)
