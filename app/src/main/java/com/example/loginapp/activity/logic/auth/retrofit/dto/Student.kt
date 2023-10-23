package com.example.loginapp.activity.logic.auth.retrofit.dto

import java.util.Date

data class Student (
    val id: String,
    val email: String,
    val emailConfirmed: Boolean,
    val fio: String,
    val englishFIO: String,
    val studentCod: String,
    val birthDate: Date,
    val snils: String,
    val foreigner: Boolean,
    val citizenship: String,
    val sex: String,
    val address: String,
    val contact: String,
    val recordBooks: List<RecordBooks>
)
