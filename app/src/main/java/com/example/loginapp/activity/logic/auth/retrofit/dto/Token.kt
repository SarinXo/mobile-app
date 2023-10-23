package com.example.loginapp.activity.logic.auth.retrofit.dto

data class Token(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int,
    val refreshToken: String
)
