package com.example.loginapp.activity.logic.auth.retrofit.dto

import java.util.Date

data class User(
    val email: String,
    val emailConfirmed: Boolean,
    val englishFIO: String,
    val teacherCod: String,
    val studentCod: String,
    val birthDate: Date,
    val academicDegree: String,
    val academicRank: String,
    val roles: List<Role>,
    val id: String,
    val userName: String,
    val fio: String,
    val photo: List<UserPhoto>
)
