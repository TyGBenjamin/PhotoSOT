package com.example.photosoftruth.data.local.db.di

import android.content.Context
import androidx.room.Room
import com.example.photosoftruth.data.local.db.PhotoDao
import com.example.photosoftruth.data.local.db.PhotoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesRoomDB(@ApplicationContext applicationContext: Context): PhotoDatabase {
        return Room.databaseBuilder(
            applicationContext,
            PhotoDatabase::class.java, "photo-database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesPhotoDao(database: PhotoDatabase): PhotoDao = database.photoDao()
}
