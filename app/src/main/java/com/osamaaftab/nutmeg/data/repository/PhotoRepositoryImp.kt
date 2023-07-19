package com.osamaaftab.nutmeg.data.repository

import com.osamaaftab.nutmeg.data.source.remote.PhotoRemoteDataSource
import com.osamaaftab.nutmeg.domain.model.PhotoModel
import com.osamaaftab.nutmeg.domain.repository.PhotoRepository

class PhotoRepositoryImp(private val photoRemoteDataSource: PhotoRemoteDataSource) :
    PhotoRepository {
    override suspend fun getPhoto(albumId: String): PhotoModel {
        return photoRemoteDataSource.getPhoto(albumId).first()
    }
}