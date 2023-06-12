package com.example.photosoftruth.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.photosoftruth.data.local.entity.Photo

@Composable
@Suppress("FunctionNaming")
    fun PhotoCard(
        photo: Photo
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black)
                .padding(5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 20.dp, start = 10.dp
                    )
            ) {
                Image(
                    painter = rememberAsyncImagePainter(photo.thumbnailUrl),
                    contentDescription = null,
                    modifier = Modifier.size(145.dp)
                )
                Column(modifier = Modifier.padding(start = 15.dp, end = 15.dp)) {
                    Text(text = photo.title)

                }
            }
        }
    }
