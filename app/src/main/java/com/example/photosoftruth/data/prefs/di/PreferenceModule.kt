package com.example.photosoftruth.data.prefs.di

import android.content.Context
import com.example.photosoftruth.data.prefs.TimePref
import com.example.photosoftruth.data.prefs.TimePrefImpl
import com.example.photosoftruth.utils.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {
    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): TimePref {
        return TimePrefImpl(context.dataStore)
    }
}
