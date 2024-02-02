package com.example.babytracker.ui.fragments.symptoms.choose_symptoms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.babytracker.data.entity.symptoms.Choose_Symptoms
import com.example.babytracker.repository.BabyTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseSymptomsViewModel @Inject constructor(val btRepo: BabyTrackerRepository) : ViewModel(){
    private val _chooseSympltomsList = MutableStateFlow<List<Choose_Symptoms>>(emptyList())
    val chooseSymptomsList : Flow<List<Choose_Symptoms>> = _chooseSympltomsList.asStateFlow()

    init {
        getSymptoms()
    }

    private fun getSymptoms() {
        viewModelScope.launch {
            btRepo.getSymptoms().collect{
                _chooseSympltomsList.value = it
            }
        }
    }
}