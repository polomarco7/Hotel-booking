package com.example.hotelbooking

data class Traveler(
    val id: Int,
    val personData: List<PersonData>
)
data class PersonData(
    val name: String,
    val surname: String,
    val birthDay: String,
    val citizenship: String,
    val passportId: Int,
    val issueDate: String
)
