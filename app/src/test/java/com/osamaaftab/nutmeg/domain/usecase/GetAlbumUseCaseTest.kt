package com.osamaaftab.nutmeg.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.osamaaftab.nutmeg.data.ErrorHandler
import com.osamaaftab.nutmeg.domain.model.AlbumModel
import com.osamaaftab.nutmeg.domain.repository.AlbumRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class GetAlbumUseCaseTest {

    @RelaxedMockK
    lateinit var albumRepository: AlbumRepository

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()

    private lateinit var sut: GetAlbumUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
        val errorHandler = mockk<ErrorHandler>()
        sut = GetAlbumUseCase(albumRepository, errorHandler)
    }

    @Test
    fun `run THEN verify the album flow object`(): Unit =
        runBlocking {
            val albumModel = mockk<AlbumModel>(relaxed = true)
            val albumFlow = flow { emit(albumModel) }
            every { runBlocking { albumRepository.getAlbum() } } returns (albumFlow)

            val album = sut.run()

            assertEquals(albumFlow, album)
        }
}