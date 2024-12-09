package com.mahin.sholpe.data.repository

import android.util.Log
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
import retrofit2.Response
import java.io.IOException

class UserRepository(private val apiService: ApiService) {

    // Helper function to handle API calls and errors
    private suspend fun <T> safeApiCall(
        retryCount: Int = 3,
        apiCall: suspend () -> Response<T>
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            repeat(retryCount) {
                try {
                    val response = apiCall()
                    if (response.isSuccessful) {
                        response.body()?.let {
                            return@withContext Resource.Success(it)
                        } ?: return@withContext Resource.Error("Empty response")
                    } else {
                        return@withContext Resource.Error("Error: ${response.message()}")
                    }
                } catch (e: IOException) {
                    if (it == retryCount - 1) {
                        return@withContext Resource.Error("Network error: ${e.message}")
                    }
                    // Retry on network error
                } catch (e: Exception) {
                    return@withContext Resource.Error("An unexpected error: ${e.localizedMessage}")
                }
            }
            Resource.Error("Failed after $retryCount retries")
        }
    }

    // Login user method using safeApiCall
    suspend fun loginUser(loginRequest: LoginRequest): Resource<LoginResponse> {
        return safeApiCall { apiService.loginUser(loginRequest) }
    }

    // Register user method using safeApiCall
    suspend fun registerUser(signupRequest: SignupRequest): Resource<SignupResponse> {
        val res = safeApiCall { apiService.registerUser(signupRequest) }
            Log.d("Repository", res.toString())
            return res
    }

    // Fetch products method using safeApiCall
    suspend fun fetchProducts(): Resource<Product> {
        return safeApiCall { apiService.getProducts() }
    }
}
