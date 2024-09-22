package com.mahin.sholpe.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahin.sholpe.data.model.signup.SignupRequest
import com.mahin.sholpe.data.model.signup.SignupResponse
import com.mahin.sholpe.data.repository.UserRepository
import com.mahin.sholpe.utils.Resource
import com.mahin.sholpe.utils.Resource.Loading
import kotlinx.coroutines.launch

class SignupViewModel(private val repository: UserRepository) : ViewModel() {

    private val _signupResult = MutableLiveData<Resource<SignupResponse>>()
    val signupResult: LiveData<Resource<SignupResponse>> = _signupResult

    fun registerUser(name: String, phone: String, email: String, password: String) {
        viewModelScope.launch {
            _signupResult.value = Resource.Loading() // Use Loading() to instantiate
            val response = repository.registerUser(SignupRequest(name, phone, email, password))
            _signupResult.value = response
        }
    }
}