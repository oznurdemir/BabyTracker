package com.example.babytracker.ui.fragments.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.babytracker.data.entity.SettingsItem
import com.example.babytracker.repository.BabyTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(val btRepo : BabyTrackerRepository): ViewModel() {
    var settingsItemList = MutableLiveData<List<SettingsItem>>()

    init {
        setItem()
    }

    private fun setItem(){
        CoroutineScope(Dispatchers.IO).launch {
            settingsItemList.postValue(btRepo.setList())
        }
    }

}