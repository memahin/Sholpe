package com.mahin.sholpe

class data {

    data class LoginRequest(
        val user_id: String,
        val password: String
    )

    data class SignupRequest(
        val name: String,
        val phone: String,
        val email: String,
        val password: String
    )

    data class LoginResponse(
        val token: String,
        val message: String
    )

    data class SignupResponse(
        val message: String
    )

    data class Product(
        val id: Int,
        val category_id: Int,
        val sub_category_id: Int,
        val brand_id: Int,
        val name: String,
        val slug: String,
        val description: String,
        val price: String,
        val discount: String,
        val shipping_cost: String,
        val quantity: Int,
        val sku: String,
        val status: Int,
        val color: String,
        val size: String,
        val main_image: String?,
        val category: Category,
        val sub_category: SubCategory,
        val brand: Brand,
        val file_manager: FileManager
    )

    data class Category(
        val id: Int,
        val name: String,
        val priority: Int?,
        val status: Int
    )

    data class SubCategory(
        val id: Int,
        val category_id: Int,
        val name: String,
        val priority: Int?,
        val status: Int
    )

    data class Brand(
        val id: Int,
        val name: String,
        val company_name: String,
        val priority: Int?,
        val status: Int
    )

    data class FileManager(
        val id: Int,
        val origin_type: String,
        val origin_id: Int,
        val url: String,
        val created_at: String,
        val updated_at: String
    )

    data class ProductResponse(
        val current_page: Int,
        val data: List<Product>
    )

}