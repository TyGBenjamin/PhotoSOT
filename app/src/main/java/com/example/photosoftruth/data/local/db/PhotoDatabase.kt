package com.example.photosoftruth.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.photosoftruth.data.local.entity.Photo
import javax.inject.Singleton

/**
 * Photo database
 *
 * @constructor Create empty Photo database
 */
@Database(entities = [Photo::class], version = 1 )
@Singleton
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun photoDao() : PhotoDao
}
