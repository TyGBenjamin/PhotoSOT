package com.example.photosoftruth.data.repositoryImpl

import com.example.photosoftruth.data.local.db.PhotoDao
import com.example.photosoftruth.data.local.entity.Photo
import com.example.photosoftruth.data.prefs.TimePref
import com.example.photosoftruth.data.remote.api.ApiService
import com.example.photosoftruth.repository.Repository
import com.example.photosoftruth.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

// TODO: Clean this up
/**
 * Repository impl
 *
 * @property apiService
 * @property photoDao
 * @property scope
 * @property timePref
 * @constructor Create empty Repository impl
 */
class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val photoDao: PhotoDao,
    private val scope: CoroutineScope,
    private val timePref: TimePref
) : Repository {

    override suspend fun getPhotos(): Resource<List<Photo>> = withContext(Dispatchers.IO) {
       if (fetchFromDatabase().isEmpty()){
           return@withContext try {
               apiResponse()
           } catch (e: IllegalArgumentException) {
               Resource.Error(e.message.toString())
           }
       } else {
           Resource.Success(fetchFromDatabase())
       }

    }

    val photosIfDbIsEmpty: Flow<List<Photo>>
        get() = photoDao.getPhotos().onEmpty {
            val photoRequest = getPhotos()
            when (photoRequest) {
                is Resource.Error -> Resource.Error("Error")
                Resource.Idle -> Resource.Idle
                Resource.Loading -> Resource.Loading
                is Resource.Success -> {
                    scope.launch { photoDao.insertPhotos(photoRequest.data) }
                    // can emit albums as well since it is a flow >> emit(photosOlay)
                    Resource.Success(photoRequest)
                }
            }
            // photosOlay.map { scope.async { photoDao.insertPhotos(it) } }.awaitAll() if using varags in DAO
        }

    val photosIfDbEmptyAndStaleCheck: Flow<List<Photo>>
        get() = photoDao.getPhotos().onEach { photos ->
            if (photos.isEmpty() || timePref.isdDataStale(System.currentTimeMillis())) {
                when (val photoRequest = getPhotos()) {
                    is Resource.Error -> Resource.Error("Error")
                    Resource.Idle -> Resource.Idle
                    Resource.Loading -> Resource.Loading
                    is Resource.Success -> {
                        scope.launch {
                            photoDao.insertPhotos(photoRequest.data)
                            timePref.saveTimeStamp(System.currentTimeMillis())
                        }
                        Resource.Success(photoRequest)
                    }
                }
            }
        }

    /**
     * Fetch from database.
     *
     * @return
     */
    suspend fun fetchFromDatabase(): List<Photo> =
        photoDao.getPhotos().firstOrNull() ?: emptyList()


    /**
     * Api response
     *
     */
    suspend fun apiResponse() = withContext(Dispatchers.IO) {
        val response = apiService.getPhotos()
        if (response.isSuccessful && response.body() != null) {
            val photoList = response.body()!!.map { it.toPhoto() }
            scope.launch {
                timePref.saveTimeStamp(System.currentTimeMillis())
                photoDao.insertPhotos(photoList)
            }
            Resource.Success(photoList)
        } else {
            Resource.Error("error retrieving post")
        }
    }
}
