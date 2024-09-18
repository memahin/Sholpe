package com.mahin.sholpe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch data and set up RecyclerView
        fetchProducts()
    }

    private fun fetchProducts() {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getProducts()
                val products = response.data
                productAdapter = ProductAdapter(products)
                recyclerView.adapter = productAdapter
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}