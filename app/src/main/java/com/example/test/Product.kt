package com.example.test

data class Product(
    val brand: String,
    val title: String,
    val price: String,
    val rating: Double,
    val reviewCount: Int,
    val imageRes: Int,
    val link: String = ""
)
