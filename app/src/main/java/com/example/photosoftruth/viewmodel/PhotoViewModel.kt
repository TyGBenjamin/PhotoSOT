package com.example.photosoftruth.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photosoftruth.data.local.entity.Photo
import com.example.photosoftruth.data.repositoryImpl.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: Clean up this class
/**
 * Photo view model
 *
 * @property repo
 * @constructor Create empty Photo view model
 */
@HiltViewModel
class PhotoViewModel @Inject constructor(private val repo: RepositoryImpl) : ViewModel() {


    var state by mutableStateOf(PhotoState())
        private set


    fun getPhotosEnhanced() = viewModelScope.launch {
        val photoList = repo.photosIfDbIsEmpty
        state = state.copy(
            isLoading = false,
            photoList = photoList.first()
        )
    }

    fun getPhotosEnhancedWithStaleCheck() = viewModelScope.launch {
        val photoList = repo.photosIfDbEmptyAndStaleCheck
        state = state.copy(
            isLoading = false,
            photoList = photoList.first()
        )
    }

    data class PhotoState(
        var photoList: List<Photo> = emptyList(),
        var isLoading: Boolean = false,
        var error: String = ""
    )
}


