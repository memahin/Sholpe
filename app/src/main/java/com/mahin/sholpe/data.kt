package com.mahin.sholpe

class data {
    data class LoginRequest(val user_id: String, val password: String)
    data class SignupRequest(val name: String, val phone: String, val email: String, val password: String)

    data class LoginResponse(val token: String, val message: String)
    data class SignupResponse(val message: String)

}