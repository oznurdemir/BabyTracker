package com.example.babytracker.ui.fragments.sleep

import androidx.lifecycle.ViewModel
import com.example.babytracker.repository.BabyTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SleepViewModel @Inject constructor(private val btRepo: BabyTrackerRepository) : ViewModel() {
    fun saveSleep(fellTime: String, wokeTime: String, note: String) {
        btRepo.saveSleep(fellTime, wokeTime, note)
    }
}