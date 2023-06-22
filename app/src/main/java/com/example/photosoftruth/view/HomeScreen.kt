package com.example.photosoftruth.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.photosoftruth.data.local.entity.Photo
import com.example.photosoftruth.viewmodel.PhotoViewModel

@Composable
fun HomeScreen(
    viewModel: PhotoViewModel,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getPhotosEnhancedWithStaleCheck()
    }
    val photoList = viewModel.state.photoList
    if (photoList.isEmpty()) {
        ProgressIndicator()
    }
    LazyColumn(state = rememberLazyListState(), modifier = Modifier.padding(5.dp)) {
        items(photoList) { photo ->
            PhotoCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Black)
                    .padding(5.dp),
                photo = photo,
            )
        }
    }
}
