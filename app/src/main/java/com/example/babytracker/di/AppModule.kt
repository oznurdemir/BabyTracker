package com.example.babytracker.di

import com.example.babytracker.data.datasource.BabyTrackerDataSource
import com.example.babytracker.repository.BabyTrackerRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesBabyTrackerDataSource(
        @Named("feeding") feedingCollectionReference: CollectionReference,
        @Named("sleep") sleepCollectionReference: CollectionReference,
        @Named("symptoms") symptomsCollectionReference: CollectionReference
    ): BabyTrackerDataSource {
        return BabyTrackerDataSource(feedingCollectionReference, sleepCollectionReference, symptomsCollectionReference)
    }

    @Provides
    @Singleton
    fun providesBabyTrackerRepository(btDataSource: BabyTrackerDataSource): BabyTrackerRepository {
        return BabyTrackerRepository(btDataSource)
    }

    @Provides
    @Singleton
    @Named("feeding")
    fun providesFeedingCollectionReference(): CollectionReference {
        return Firebase.firestore.collection("Feeding")
    }

    @Provides
    @Singleton
    @Named("sleep")
    fun providesSleepCollectionReference(): CollectionReference {
        return Firebase.firestore.collection("Sleep")
    }

    @Provides
    @Singleton
    @Named("symptoms")
    fun providesSymptomsCollentionReferance() : CollectionReference {
        return Firebase.firestore.collection("Symptoms")
    }
}