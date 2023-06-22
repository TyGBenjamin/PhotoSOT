package com.example.photosoftruth

import com.example.photosoftruth.data.local.entity.Photo
import com.example.photosoftruth.data.repositoryImpl.RepositoryImpl
import com.example.photosoftruth.utilTest.CoroutinesTestExtension
import com.example.photosoftruth.utilTest.InstantTaskExecutorExtension
import com.example.photosoftruth.utils.Resource
import com.example.photosoftruth.viewmodel.PhotoViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantTaskExecutorExtension::class)
class PhotoViewModelTest {

    @RegisterExtension
    private val coroutinesTestExtension: CoroutinesTestExtension = CoroutinesTestExtension()


    @MockK
    private val repo = mockk<RepositoryImpl>()
    private val testViewModel: PhotoViewModel = PhotoViewModel(repo)


    @BeforeEach
    fun setUp() = runBlocking {
        // Given
        var testGetPhotos =
            Resource.Success(
                listOf(
                    Photo(
                        id = 999,
                        albumId = 998,
                        thumbnailUrl = "",
                        url = "",
                        title = ""
                    )
                )
            )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @org.junit.jupiter.api.Test
    fun testDefaultValues() = runTest(coroutinesTestExtension.dispatcher) {
        // Given
        val testList = testViewModel.state.photoList

        // Then
        assertEquals(testList, Resource.Loading)
        Assertions.assertFalse(testViewModel.state.isLoading)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @org.junit.jupiter.api.Test
    fun testGetList() = runTest(coroutinesTestExtension.dispatcher) {

        // Given
        var testGetPhotos =
            Resource.Success(
             emptyList<Photo>()
            )
        coEvery { repo.getPhotos() } coAnswers { testGetPhotos }

        // When
        testViewModel.getPhotos()

        // Then
        val photoListTest = testViewModel.state.photoList

        Assertions.assertFalse(testViewModel.state.isLoading)
        Assertions.assertEquals(testGetPhotos, photoListTest)
    }
}
