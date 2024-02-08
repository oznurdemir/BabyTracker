package com.example.babytracker.ui.fragments.calender

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.babytracker.data.entity.calender.CalenderItem
import com.example.babytracker.repository.BabyTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalenderViewModel @Inject constructor(private val btRepo: BabyTrackerRepository) : ViewModel() {
    val dataList = MutableLiveData<List<CalenderItem>>()

    fun getSymptomsData() {
        viewModelScope.launch {
            btRepo.getSymptomsData().collect {
                dataList.value = it
            }
        }
    }

    fun getFeedingData() {
        viewModelScope.launch {
            btRepo.getFeedingData().collect {
                dataList.value = it
            }
        }
    }
}