package com.example.hotelbooking.models

data class Traveler(
    var id: Int = 0,
    var name: String? = "",
    var surname: String? = "",
    var birthDay: String? = "",
    var citizenship: String? = "",
    var passportId: String = "",
    var issueDate: String? = ""
)
