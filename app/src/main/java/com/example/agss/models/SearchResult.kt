package com.example.agss.models

data class SearchResult(
    val id: String,
    val name: String,
    val location: String,
    val price: String,
    val imageResId: Int,
    val description: String,
    val latitude: Double,
    val longitude: Double
) 