package com.example.babytracker.ui.fragments.feeding

import androidx.lifecycle.ViewModel
import com.example.babytracker.repository.BabyTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedingViewModel @Inject constructor(val btRepo : BabyTrackerRepository): ViewModel() {
    fun saveFeeding(time : String, amount : String, note : String){
        btRepo.saveFeeding(time, amount, note)
    }
}