package com.example.hotelbooking.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoomsList(
    @Json(name = "rooms") val rooms: List<Rooms> = emptyList()
)

@JsonClass(generateAdapter = true)
data class Rooms(
    val id: Int,
    val name: String,
    val price: Long,
    @Json(name = "price_per")val pricePer: String,
    val peculiarities: List<String>,
    @Json(name = "image_urls")val imageUrls: List<String>
    )

