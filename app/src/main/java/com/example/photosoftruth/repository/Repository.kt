package com.example.photosoftruth.repository

import com.example.photosoftruth.data.local.entity.Photo
import com.example.photosoftruth.utils.Resource

interface Repository {

    suspend fun getPhotos(): Resource<List<Photo>>
}
