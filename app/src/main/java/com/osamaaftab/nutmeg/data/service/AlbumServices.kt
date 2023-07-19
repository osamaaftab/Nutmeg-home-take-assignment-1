package com.osamaaftab.nutmeg.data.service

import com.osamaaftab.nutmeg.domain.model.AlbumModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface AlbumServices {
    @GET("albums")
    fun getAlbumsAsync(): Deferred<List<AlbumModel>>
}