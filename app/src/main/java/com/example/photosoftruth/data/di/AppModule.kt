package com.example.photosoftruth.data.di

import com.example.photosoftruth.data.remote.api.ApiService
import com.example.photosoftruth.data.repositoryImpl.RepositoryImpl
import com.example.photosoftruth.repository.Repository
import com.example.photosoftruth.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * App module.
 *
 * @constructor Create empty App module
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApiService(): ApiService {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRepostioryImpl(apiService: ApiService): Repository = RepositoryImpl(apiService)
}
