package com.example.photosoftruth.data.repositoryImpl

import com.example.photosoftruth.data.local.entity.Photo
import com.example.photosoftruth.data.remote.api.ApiService
import com.example.photosoftruth.repository.Repository
import com.example.photosoftruth.utils.Resource
import java.lang.IllegalArgumentException
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {
    override suspend fun getPhotos(): Resource<List<Photo>> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = apiService.getPhotos()
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!.map { it.toPhoto() })
            } else {
                Resource.Error("error retreiving post")
            }
        } catch (e: IllegalArgumentException) {
            Resource.Error(e.message.toString())
        }
    }
}
