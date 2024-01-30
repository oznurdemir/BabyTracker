package com.example.babytracker.data.datasource

import com.example.babytracker.R
import com.example.babytracker.data.entity.SavedFeeding
import com.example.babytracker.data.entity.SavedSleep
import com.example.babytracker.data.entity.SettingsItem
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Named

class BabyTrackerDataSource(
    @Named("feeding") private val feedingCollectionReference: CollectionReference,
    @Named("sleep") private val sleepCollectionReference: CollectionReference
) {
    suspend fun setList(): Flow<List<SettingsItem>> = flow {
        val s1 = SettingsItem(1, R.drawable.settings_page2, R.string.rate_us)
        val s2 = SettingsItem(2, R.drawable.settings_page3, R.string.terms_of_us)
        val s3 = SettingsItem(3, R.drawable.settings_page4, R.string.privacy_pol)
        val s4 = SettingsItem(4, R.drawable.settings_page5, R.string.contact_us)
        val s5 = SettingsItem(5, R.drawable.settings_page6, R.string.restore_pur)

        val initializedSettingsItem = ArrayList<SettingsItem>()
        initializedSettingsItem.add(s1)
        initializedSettingsItem.add(s2)
        initializedSettingsItem.add(s3)
        initializedSettingsItem.add(s4)
        initializedSettingsItem.add(s5)

        emit(initializedSettingsItem)// Flow API'sinde, bir akış içinde bir değeri göndermek için kullanılan bir fonksiyondur.
    }.flowOn(Dispatchers.IO)

    fun saveFeeding(time: String, amount: String, note: String) {
        val newItem = SavedFeeding(time = time, amount = amount, note = note, category = "feeding")
        feedingCollectionReference.add(newItem)
            .addOnFailureListener { }
    }

    fun saveSleep(fellTime: String, wokeTime: String, note: String) {
        val newItem =
            SavedSleep(fellTime = fellTime, wokeTime = wokeTime, note = note, category = "sleep")
        sleepCollectionReference.add(newItem)
            .addOnFailureListener { }
    }
}