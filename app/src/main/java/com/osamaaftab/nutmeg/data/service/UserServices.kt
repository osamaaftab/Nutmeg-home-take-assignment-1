package com.osamaaftab.nutmeg.data.service

import com.osamaaftab.nutmeg.domain.model.UserModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface UserServices {

    @GET("users/{userId}")
    fun getUsersAsync(
        @Path("userId") userId: String
    ): Deferred<UserModel>
}