package com.example.babytracker.ui.fragments.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.babytracker.data.entity.SettingsItem
import com.example.babytracker.repository.BabyTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(val btRepo : BabyTrackerRepository): ViewModel() {
    private val _settingsItemList = MutableStateFlow<List<SettingsItem>>(emptyList())
    val settingsItemList: Flow<List<SettingsItem>> = _settingsItemList.asStateFlow()

    init {
        setItem()
    }

    private fun setItem(){
        viewModelScope.launch {
            btRepo.setList().collect { settings ->
                _settingsItemList.value = settings
            }
        }
    }

}