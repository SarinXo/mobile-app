package com.example.loginapp.activity.logic.auth.retrofit.dto

import java.time.Year

data class RecordBooks(
    val cod: String,
    val number: String,
    val lastYear: Int,
    val course: String,
    val faculty: String,
    val educationForm: String,
    val educationLevel: String,
    val educationBasis: String,
    val specialty: String,
    val status: String,
    val disable: Boolean
)
