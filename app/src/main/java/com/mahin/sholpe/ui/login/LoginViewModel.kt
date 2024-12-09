package com.mahin.sholpe.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahin.sholpe.data.model.login.LoginRequest
import com.mahin.sholpe.data.model.login.LoginResponse
import com.mahin.sholpe.data.repository.UserRepository
import com.mahin.sholpe.utils.Resource
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<Resource<LoginResponse>>()
    val loginResult: LiveData<Resource<LoginResponse>> = _loginResult

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _loginResult.value = Resource.Loading() // Use Loading() to instantiate
            val response = repository.loginUser(LoginRequest(user_id = email, password = password))
            _loginResult.value = response
        }
    }
}
