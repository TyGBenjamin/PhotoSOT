package com.example.photosoftruth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.photosoftruth.ui.theme.PhotosOfTruthTheme
import com.example.photosoftruth.view.HomeScreen
import com.example.photosoftruth.viewmodel.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Move logic to HomeScreen Composable
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val photoViewModel by viewModels<PhotoViewModel>()
            PhotosOfTruthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(viewModel = photoViewModel)
                }
            }
        }
    }
}

