package com.example.babytracker.repository

import com.example.babytracker.data.datasource.BabyTrackerDataSource
import com.example.babytracker.data.entity.SettingsItem
import kotlinx.coroutines.flow.Flow

class BabyTrackerRepository(val btDataSource : BabyTrackerDataSource) {
    suspend fun setList(): Flow<List<SettingsItem>> = btDataSource.setList()
    fun saveFeeding(time : String, amount : String, note : String) = btDataSource.saveFeeding(time, amount, note)
}