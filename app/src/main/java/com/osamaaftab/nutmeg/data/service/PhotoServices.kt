package com.osamaaftab.nutmeg.data.service

import com.osamaaftab.nutmeg.domain.model.PhotoModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotoServices {

    @GET("albums/{albumId}/photos")
    fun getPhotosAsync(
        @Path("albumId") albumId: String
    ): Deferred<List<PhotoModel>>
}