package com.example.photosoftruth.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.photosoftruth.data.local.entity.Photo
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow


@Dao
@Singleton
interface PhotoDao {
    @Query("SELECT * FROM photos")
    fun getPhotos(): Flow<List<Photo>>

    @Query("SELECT * FROM photos WHERE id in (:id)")
    fun getPhotoById(id: String) : Photo

    @Update
    fun updatePhoto(photo: Photo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoto(photo: Photo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotos(photos: List<Photo>)

    @Delete
    fun deletePhoto(photo: Photo)
}

