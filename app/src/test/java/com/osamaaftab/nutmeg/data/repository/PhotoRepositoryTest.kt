package com.osamaaftab.nutmeg.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.osamaaftab.nutmeg.data.source.remote.PhotoRemoteDataSource
import com.osamaaftab.nutmeg.domain.model.PhotoModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class PhotoRepositoryTest {

    @MockK
    lateinit var photoRemoteDataSource: PhotoRemoteDataSource

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()

    private lateinit var sut: PhotoRepositoryImp

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
        sut = PhotoRepositoryImp(photoRemoteDataSource)
    }


    @Test
    fun `getPhoto from remote data THEN verify if the first object of Photo is returned`(): Unit =
        runBlocking {
            val listPhotoModel = listOf(PhotoModel("", "", ""))
            every {
                runBlocking { photoRemoteDataSource.getPhoto("albumId") }
            } returns (listPhotoModel)

            val photo = sut.getPhoto("albumId")

            assertEquals(listPhotoModel.first(), photo)
        }
}