package com.mahin.sholpe.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mahin.sholpe.R
import com.mahin.sholpe.data.remote.ApiClient
import com.mahin.sholpe.data.repository.UserRepository
import com.mahin.sholpe.utils.Resource

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(UserRepository(ApiClient.api))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeViewModel()
        viewModel.loadProducts()
    }

    private fun observeViewModel() {
        viewModel.products.observe(this, Observer { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Show loading
                }
                is Resource.Success -> {
                    // Update UI with product data
                    resource.data?.let { product ->
                        // Handle the product data
                    }
                }
                is Resource.Error -> {
                    // Handle error
                }
            }
        })
    }
}
