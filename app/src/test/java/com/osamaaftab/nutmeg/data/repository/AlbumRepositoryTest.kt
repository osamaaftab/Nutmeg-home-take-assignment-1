package com.osamaaftab.nutmeg.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.osamaaftab.nutmeg.data.source.remote.AlbumRemoteDataSource
import com.osamaaftab.nutmeg.domain.model.AlbumModel
import com.osamaaftab.nutmeg.domain.repository.PhotoRepository
import com.osamaaftab.nutmeg.domain.repository.UserRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AlbumRepositoryTest {

    @MockK
    lateinit var albumRemoteDataSource: AlbumRemoteDataSource

    @RelaxedMockK
    lateinit var userRepository: UserRepository

    @RelaxedMockK
    lateinit var photoRepository: PhotoRepository

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()

    private lateinit var sut: AlbumRepositoryImp


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
        sut = AlbumRepositoryImp(albumRemoteDataSource, photoRepository, userRepository)
    }

    @Test
    fun `getAlbums from remote data source and getUser and getPhoto THEN verify if they are mapped to album object`(): Unit =
        runBlocking {
            val albumModel = AlbumModel("userId", "title", "id", null, null, null)

            every {
                runBlocking { albumRemoteDataSource.getAlbums() }
            } returns (listOf(albumModel))

            sut.getAlbum().collectLatest {
                verify(exactly = 1) { runBlocking { userRepository.getUser(it.userId) } }
                verify(exactly = 1) { runBlocking { photoRepository.getPhoto(it.id) } }
            }
        }
}