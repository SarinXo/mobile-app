package com.example.loginapp.activity.logic.auth.retrofit.dto

data class TokenRequest(
   val grant_type: String = "password",
   val username: String,
   val password: String,
   val client_id: String = "8",
   val client_secret: String = "qweasd"
)
