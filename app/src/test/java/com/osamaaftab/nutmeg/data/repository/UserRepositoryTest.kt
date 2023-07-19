package com.osamaaftab.nutmeg.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.osamaaftab.nutmeg.data.source.remote.UserRemoteDataSource
import com.osamaaftab.nutmeg.domain.model.UserModel
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

class UserRepositoryTest {

    @MockK
    lateinit var userRemoteDataSource: UserRemoteDataSource

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()

    private lateinit var sut: UserRepositoryImp


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
        sut = UserRepositoryImp(userRemoteDataSource)
    }

    @Test
    fun `getUser from remote data THEN verify if the object which is returned`(): Unit =
        runBlocking {
            val userModel = UserModel("", "")
            every {
                runBlocking { userRemoteDataSource.getUser("userId") }
            } returns (userModel)

            val user = sut.getUser("userId")

            assertEquals(userModel, user)
        }
}