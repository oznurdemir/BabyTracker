package com.example.babytracker.data.datasource

import com.example.babytracker.R
import com.example.babytracker.data.entity.SavedFeeding
import com.example.babytracker.data.entity.SavedSleep
import com.example.babytracker.data.entity.SettingsItem
import com.example.babytracker.data.entity.calender.CalenderItem
import com.example.babytracker.data.entity.symptoms.Choose_Symptoms
import com.example.babytracker.data.entity.symptoms.SavedSymptoms
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Named

class BabyTrackerDataSource(
    @Named("feeding") private val feedingCollectionReference: CollectionReference,
    @Named("sleep") private val sleepCollectionReference: CollectionReference,
    @Named("symptoms") private val symptomsCollectionReference: CollectionReference
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

    suspend fun getSymptoms() : Flow<List<Choose_Symptoms>> = flow {
        val s1 = Choose_Symptoms(R.drawable.runny_nose, "Runny Nose")
        val s2 = Choose_Symptoms(R.drawable.heartburn, "Heartburn")
        val s3 = Choose_Symptoms(R.drawable.no_appetite, "No Appetite")
        val s4 = Choose_Symptoms(R.drawable.rush, "Rush")
        val s5 = Choose_Symptoms(R.drawable.low_enegy, "Low Enegy")
        val s6 = Choose_Symptoms(R.drawable.nausea, "Nausea")
        val s7 = Choose_Symptoms(R.drawable.cough, "Cough")
        val s8 = Choose_Symptoms(R.drawable.fever, "Fever")

        val initializedSymptomsItem = ArrayList<Choose_Symptoms>()
        initializedSymptomsItem.add(s1)
        initializedSymptomsItem.add(s2)
        initializedSymptomsItem.add(s3)
        initializedSymptomsItem.add(s4)
        initializedSymptomsItem.add(s5)
        initializedSymptomsItem.add(s6)
        initializedSymptomsItem.add(s7)
        initializedSymptomsItem.add(s8)

        emit(initializedSymptomsItem)
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

    fun saveSymptoms(time : String, symptoms: String, note: String) {
        val newItem = SavedSymptoms(time = time, symptoms = symptoms, note = note, category = "symptoms")
        symptomsCollectionReference.add(newItem)
            .addOnFailureListener { }
    }

    suspend fun getSymptomsData(): Flow<List<CalenderItem>> = flow {
        val snapshot = symptomsCollectionReference
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .get()
            .await()

        val symptomsData = snapshot.toObjects(SavedSymptoms::class.java)
            .map { savedSymptoms ->
                CalenderItem(
                    time = savedSymptoms.time,
                    category = savedSymptoms.category,
                    createdAt = savedSymptoms.createdAt
                )
            }

        emit(symptomsData)
    }.flowOn(Dispatchers.IO)


}