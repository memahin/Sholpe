package com.mahin.sholpe.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahin.sholpe.data.model.product.Product
import com.mahin.sholpe.data.repository.UserRepository
import com.mahin.sholpe.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository) : ViewModel() {

    private val _products = MutableLiveData<Resource<Product>>()
    val products: LiveData<Resource<Product>> = _products

    fun loadProducts() {
        viewModelScope.launch {
            _products.value = Resource.Loading()
            val response = repository.fetchProducts()
            _products.value = response
        }
    }
}