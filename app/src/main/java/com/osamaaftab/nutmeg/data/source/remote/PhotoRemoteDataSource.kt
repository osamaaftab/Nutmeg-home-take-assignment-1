package com.osamaaftab.nutmeg.data.source.remote

import com.osamaaftab.nutmeg.data.service.PhotoServices
import com.osamaaftab.nutmeg.data.source.base.PhotoDataSource
import com.osamaaftab.nutmeg.domain.model.PhotoModel

class PhotoRemoteDataSource(private val photoServices: PhotoServices) : PhotoDataSource {
    override suspend fun getPhoto(albumId: String): List<PhotoModel> {
        return photoServices.getPhotosAsync(albumId).await()
    }
}