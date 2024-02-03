package com.example.babytracker.ui.fragments.symptoms

import androidx.lifecycle.ViewModel
import com.example.babytracker.repository.BabyTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SymptomsViewModel @Inject constructor(private val btRepo : BabyTrackerRepository): ViewModel(){
    fun saveSymptoms(time : String, symptoms : String, note : String){
        btRepo.saveSymptoms(time, symptoms, note)
    }
}