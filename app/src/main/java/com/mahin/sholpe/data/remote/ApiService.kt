package com.mahin.sholpe.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import com.mahin.sholpe.data.model.login.LoginRequest
import com.mahin.sholpe.data.model.login.LoginResponse
import com.mahin.sholpe.data.model.signup.SignupRequest
import com.mahin.sholpe.data.model.signup.SignupResponse

interface ApiService {

    @POST("login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("signup")
    suspend fun registerUser(@Body signupRequest: SignupRequest): Response<SignupResponse>
}
