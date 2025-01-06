package com.example.agss.models

data class TimeSlot(
    val time: String,
    var isAvailable: Boolean = true,
    var isSelected: Boolean = false
) 