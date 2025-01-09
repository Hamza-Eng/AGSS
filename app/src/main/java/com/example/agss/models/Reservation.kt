package com.example.agss.models

data class Reservation(
    val id: String = System.currentTimeMillis().toString(),
    val stadiumName: String,
    val date: String,
    val time: String,
    val price: String,
    val userName: String
) 