package com.example.babytracker.repository

import com.example.babytracker.data.datasource.BabyTrackerDataSource
import com.example.babytracker.data.entity.SettingsItem

class BabyTrackerRepository(val btDataSource : BabyTrackerDataSource) {
    suspend fun setList():ArrayList<SettingsItem> = btDataSource.setList()
}