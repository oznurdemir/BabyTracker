package com.example.babytracker.repository

import com.example.babytracker.data.datasource.BabyTrackerDataSource
import com.example.babytracker.data.entity.SettingsItem
import com.example.babytracker.data.entity.calender.CalenderItem
import com.example.babytracker.data.entity.symptoms.Choose_Symptoms
import kotlinx.coroutines.flow.Flow

class BabyTrackerRepository(private val btDataSource: BabyTrackerDataSource) {
    suspend fun setList(): Flow<List<SettingsItem>> = btDataSource.setList()
    suspend fun getSymptoms() : Flow<List<Choose_Symptoms>> = btDataSource.getSymptoms()
    fun saveFeeding(time: String, amount: String, note: String) =
        btDataSource.saveFeeding(time, amount, note)
    fun saveSleep(fellTime: String, wokeTime: String, note: String) =
        btDataSource.saveSleep(fellTime, wokeTime, note)

    fun saveSymptoms(time : String, symptoms: String, note: String) =
        btDataSource.saveSymptoms(time, symptoms, note)

    suspend fun getSymptomsData(): Flow<List<CalenderItem>> {
        return btDataSource.getSymptomsData()
    }

    suspend fun getFeedingData(): Flow<List<CalenderItem>> {
        return btDataSource.getFeedingData()
    }

    suspend fun getSleepData(): Flow<List<CalenderItem>> {
        return btDataSource.getSleepData()
    }
}