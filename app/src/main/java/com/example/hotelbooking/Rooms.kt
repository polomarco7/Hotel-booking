package com.example.hotelbooking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoomsList(
    @Json(name = "rooms") val rooms: List<Rooms>
)

@JsonClass(generateAdapter = true)
data class Rooms(
    val id: Int,
    val name: String,
    val price: Long,
    val pricePer: String,
    val peculiarities: List<String>,
    val imageUrls: List<String>
    )

