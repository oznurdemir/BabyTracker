package com.example.babytracker.data.entity

data class SavedSleep(
    val fellTime: String,
    val wokeTime: String,
    val note: String,
    val category: String,
    val createdAt: Long = System.currentTimeMillis()
)