package com.example.photosoftruth.data.repositoryImpl

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.photosoftruth.data.local.db.PhotoDao
import com.example.photosoftruth.data.local.db.PhotoDatabase
import com.example.photosoftruth.data.local.entity.Photo
import com.example.photosoftruth.data.prefs.TimePref
import com.example.photosoftruth.data.remote.api.ApiService
import com.example.photosoftruth.data.remote.dto.PhotoDTO
import com.example.photosoftruth.utilTest.CoroutinesTestExtension
import com.example.photosoftruth.utilTest.InstantTaskExecutorExtension
import com.example.photosoftruth.utils.Resource
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension
import retrofit2.Response


/**
 * Repository impl test.
 *
 * @constructor Create empty Repository impl test
 */
@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantTaskExecutorExtension::class)
internal class RepositoryImplTest {

    @RegisterExtension
    private val coroutinesTestExtension: CoroutinesTestExtension = CoroutinesTestExtension()


    @MockK
    private val testService = mockk<ApiService>()
    private val testDao = mockk<PhotoDao>()
    private val testPref = mockk<TimePref>()
    private val testScope: CoroutineScope = TestScope(coroutinesTestExtension.dispatcher)
    private lateinit var photoDao: PhotoDao
    private lateinit var db: PhotoDatabase


    private val testRepo = RepositoryImpl(
        apiService = testService,
        photoDao = testDao,
        scope = testScope,
        timePref = testPref,
    )


    @BeforeEach
    fun setUp() = runBlocking {

        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, PhotoDatabase::class.java).build()
        photoDao = db.photoDao()
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

    /**
     * Test fecth albums.
     *
     */
    @Test
    @DisplayName("Testing photos with StaleCheck when data is empty")
    fun testFecthAlbums() = runTest {
        //Given
        val testPhotoList = listOf<PhotoDTO>()

        val photoDTO = PhotoDTO(
            albumId = 998,
            id = 999,
            thumbnailUrl = "",
            title = "",
            url = "",
            )

        coEvery { testDao.getPhotos() } answers { flowOf(emptyList()) }
        // returns nothing
        coEvery { testDao.insertPhotos(testPhotoList.map { it.toPhoto() }) } answers { }
        coEvery { testService.getPhotos() } coAnswers { Response.success(listOf(photoDTO)) }
        coEvery { testPref.saveTimeStamp(any()) } coAnswers {  }

        val updates: MutableList<List<Photo>> = mutableListOf()

        //When
        val job = launch { testRepo.photosWithStaleCheck.toList(updates) }

        //Then
        updates.onEachIndexed { index, photos -> println("Update #${index + 1}: $photos") }
        Assertions.assertEquals(emptyList<Photo>(), updates)
        job.cancel()
    }
}
