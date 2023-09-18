package com.example.hotelbooking.models

data class HintData(
    val groupName: String,
    val textViewHint: List<TextViewHint>
)

data class TextViewHint(
    val name: String = "Имя",
    val surname: String = "Фамилия",
    val birthDay: String = "Дата рождения",
    val citizenship: String = "Гражданство",
    val passportId: String = "Номер загранпаспорта",
    val issueDate: String = "Срок действия загранпаспорта"
)