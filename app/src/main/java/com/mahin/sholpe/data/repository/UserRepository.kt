package com.mahin.sholpe.data.repository

import com.mahin.sholpe.data.model.login.LoginRequest
import com.mahin.sholpe.data.model.login.LoginResponse
import com.mahin.sholpe.data.model.product.Product
import com.mahin.sholpe.data.model.signup.SignupRequest
import com.mahin.sholpe.data.model.signup.SignupResponse
import com.mahin.sholpe.data.remote.ApiService
import com.mahin.sholpe.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class UserRepository(private val apiService: ApiService) {

    suspend fun loginUser(loginRequest: LoginRequest): Resource<LoginResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.loginUser(loginRequest)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        return@withContext Resource.Success(body)
                    }
                    return@withContext Resource.Error("Empty response")
                } else {
                    return@withContext Resource.Error("Error: ${response.message()}")
                }
            } catch (e: HttpException) {
                return@withContext Resource.Error(e.message ?: "HTTP error occurred")
            } catch (e: IOException) {
                return@withContext Resource.Error("Network error occurred: ${e.message}")
            } catch (e: Exception) {
                return@withContext Resource.Error("An unexpected error occurred: ${e.localizedMessage}")
            }
        }
    }

    suspend fun registerUser(signupRequest: SignupRequest): Resource<SignupResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.registerUser(signupRequest)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        return@withContext Resource.Success(body)
                    }
                    return@withContext Resource.Error("Empty response")
                } else {
                    return@withContext Resource.Error("Error: ${response.message()}")
                }
            } catch (e: HttpException) {
                return@withContext Resource.Error(e.message ?: "HTTP error occurred")
            } catch (e: IOException) {
                return@withContext Resource.Error("Network error occurred: ${e.message}")
            } catch (e: Exception) {
                return@withContext Resource.Error("An unexpected error occurred: ${e.localizedMessage}")
            }
        }
    }

    suspend fun fetchProducts(): Resource<Product> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProducts()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.Success(it)
                    } ?: Resource.Error("Empty response")
                } else {
                    Resource.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
            }
        }
    }
}

