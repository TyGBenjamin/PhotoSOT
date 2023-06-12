package com.example.photosoftruth.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photosoftruth.data.local.entity.Photo
import com.example.photosoftruth.data.repositoryImpl.RepositoryImpl
import com.example.photosoftruth.utils.Constants
import com.example.photosoftruth.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class PhotoViewModel @Inject constructor(private val repo: RepositoryImpl): ViewModel() {

     var state by mutableStateOf(PhotoState())
    private set

    fun getPhotos() = viewModelScope.launch {
        state = state.copy(
            isLoading = true
        )
        delay(Constants.DELAY_TIME)
        val request = repo.getPhotos()
        when(request) {
            is Resource.Error -> Resource.Error(request.errorMessage)
            Resource.Idle -> Resource.Idle
            Resource.Loading -> Resource.Loading
            is Resource.Success -> {
                state = state.copy(
                    isLoading = false,
                    photoList = Resource.Success(request.data)
                )
            }
        }
    }
    data class PhotoState(
        var photoList: Resource<List<Photo>> = Resource.Loading,
        var isLoading: Boolean = false,
        var error:String = ""
    )
}


