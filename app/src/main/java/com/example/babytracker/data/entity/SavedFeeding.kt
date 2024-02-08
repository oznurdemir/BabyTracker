package com.example.babytracker.data.entity

data class SavedFeeding(
    val time: String,
    val amount: String,
    val note: String,
    val category : String,
    val createdAt: Long = System.currentTimeMillis()
){
    // Boş yapıcı ekleyin
    constructor() : this("", "", "", "")
}

