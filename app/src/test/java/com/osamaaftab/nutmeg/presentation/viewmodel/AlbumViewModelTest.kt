package com.osamaaftab.nutmeg.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.osamaaftab.nutmeg.data.ErrorHandler
import com.osamaaftab.nutmeg.domain.model.AlbumModel
import com.osamaaftab.nutmeg.domain.usecase.GetAlbumUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
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
import kotlin.test.assertTrue

class AlbumViewModelTest {

    @RelaxedMockK
    lateinit var getAlbumUseCase: GetAlbumUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: AlbumViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
        sut = AlbumViewModel(getAlbumUseCase)
    }

    @Test
    fun `getAlbumOnSuccess WHEN album is provided THEN verify progress bar live data value is false and album list posts to live data contains the latest album`() =
        runBlocking {
            val albumModel = mockk<AlbumModel>(relaxed = true)

            val albumFlow = flow { emit(albumModel) }
            every { runBlocking { getAlbumUseCase.run() } } returns (albumFlow)

            sut.getAlbumUseCaseResponse().onSuccess(albumFlow)

            val response = sut.getAlbumListLiveData().value
            val state = sut.getShowProgressLiveData().value

            assertEquals(false, state)
            assertTrue { response!!.contains(albumModel) }
        }

    @Test
    fun `getAlbumOnError WHEN error is provided THEN verify progress bar live data value is false and error live data value is true`() {
        val errorHandler = ErrorHandler()
        val throwable = mockk<Throwable>()
        errorHandler.traceErrorException(throwable)

        sut.getAlbumUseCaseResponse()
            .onError(errorHandler.traceErrorException(throwable))

        val state = sut.getShowProgressLiveData().value
        val drawable = sut.getShowErrorLiveData().value
        assertEquals(true, drawable)
        assertEquals(false, state)
    }

    @Test
    fun `loadAlbumList WHEN album view model is initialised THEN verify progress bar live data value is true and invoke is called`() {
        val state = sut.getShowProgressLiveData().value
        assertEquals(true, state)
        verify(exactly = 1) { getAlbumUseCase.invoke(any(), null, any()) }
    }
}