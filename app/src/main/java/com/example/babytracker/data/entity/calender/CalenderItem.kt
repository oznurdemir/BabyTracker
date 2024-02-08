package com.example.babytracker.data.entity.calender

data class CalenderItem (
    val time : String,
    val note: String,
    val category: String,
    val symptoms: String,
    val amount: String,
    val fellTime: String,
    val createdAt: Long = System.currentTimeMillis()
)