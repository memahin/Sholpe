package com.mahin.sholpe

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("user/login")
    fun loginUser(@Body requestBody: data.LoginRequest): Call<data.LoginResponse>

    @POST("user/register")
    fun registerUser(@Body requestBody: data.SignupRequest): Call<data.SignupResponse>
}