package com.example.babytracker.data.entity.calender

data class CalenderItem (
    val time : String,
    val category: String,
    val createdAt: Long = System.currentTimeMillis()
)