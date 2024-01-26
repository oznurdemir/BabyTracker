package com.example.babytracker.di

import com.example.babytracker.data.datasource.BabyTrackerDataSource
import com.example.babytracker.repository.BabyTrackerRepository
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
    fun providesBabyTrackerDataSource() : BabyTrackerDataSource{
        return  BabyTrackerDataSource()
    }

    @Provides
    @Singleton
    fun providesBabyTrackerRepository(btDataSource : BabyTrackerDataSource) : BabyTrackerRepository{
        return BabyTrackerRepository(btDataSource)
    }
}