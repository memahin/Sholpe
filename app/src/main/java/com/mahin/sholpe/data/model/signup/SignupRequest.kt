package com.mahin.sholpe.data.model.signup

data class SignupRequest(
    val name: String,
    val phone: String,
    val email: String,
    val password: String
)