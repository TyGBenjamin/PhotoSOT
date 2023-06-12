package com.example.photosoftruth.data.remote.api

import com.example.photosoftruth.data.remote.dto.PhotoDTO
import retrofit2.Response
import retrofit2.http.GET

/**
 * Api service.
 *
 * @constructor Create empty Api service
 */
interface ApiService {
    @GET(PHOTO_ENDPOINT)
    suspend fun getPhotos(): Response<List<PhotoDTO>>
    companion object {
        const val PHOTO_ENDPOINT ="photos"
    }
}
