package com.example.photosoftruth.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.photosoftruth.data.local.entity.Photo

@Composable
@Suppress("FunctionNaming")
fun HomeScreen(
    photoList: List<Photo>
) {
    LazyColumn(state = rememberLazyListState(), modifier = Modifier.padding(5.dp)) {
        items(photoList){ photo ->
            PhotoCard(photo = photo)
        }
    }
}
