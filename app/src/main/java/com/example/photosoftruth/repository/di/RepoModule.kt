package com.example.photosoftruth.repository.di

import com.example.photosoftruth.data.local.db.PhotoDao
import com.example.photosoftruth.data.prefs.TimePref
import com.example.photosoftruth.data.remote.api.ApiService
import com.example.photosoftruth.data.repositoryImpl.RepositoryImpl
import com.example.photosoftruth.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {


    @Provides
    @Singleton
    fun provideRepoScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

    @Provides
    fun providesRepostioryImpl(apiService: ApiService, photoDao: PhotoDao, scope: CoroutineScope, timePref: TimePref):
        Repository = RepositoryImpl(apiService, photoDao,scope,timePref )
}
