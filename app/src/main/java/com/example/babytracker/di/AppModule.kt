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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesBabyTrackerDataSource(collectionReference: CollectionReference) : BabyTrackerDataSource{
        return  BabyTrackerDataSource(collectionReference)
    }

    @Provides
    @Singleton
    fun providesBabyTrackerRepository(btDataSource : BabyTrackerDataSource) : BabyTrackerRepository{
        return BabyTrackerRepository(btDataSource)
    }

    @Provides
    @Singleton
    fun providesCollectionReference() :CollectionReference {
        return Firebase.firestore.collection("Feeding")
    }
}