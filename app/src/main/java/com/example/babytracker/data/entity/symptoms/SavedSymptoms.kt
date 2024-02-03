package com.example.babytracker.data.entity.symptoms

data class SavedSymptoms (
    val time : String,
    val symptoms : String,
    val note : String,
    val category : String,
    val createdAt: Long = System.currentTimeMillis()
)