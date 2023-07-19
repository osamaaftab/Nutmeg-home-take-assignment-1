package com.osamaaftab.nutmeg.data.source.remote

import com.osamaaftab.nutmeg.data.service.AlbumServices
import com.osamaaftab.nutmeg.data.source.base.AlbumDataSource
import com.osamaaftab.nutmeg.domain.model.AlbumModel

class AlbumRemoteDataSource(private val albumServices: AlbumServices) : AlbumDataSource {
    override suspend fun getAlbums(): List<AlbumModel> {
        return albumServices.getAlbumsAsync().await()
    }
}