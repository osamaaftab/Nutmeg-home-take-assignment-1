package com.osamaaftab.nutmeg.data.source.base

import com.osamaaftab.nutmeg.domain.model.AlbumModel

interface AlbumDataSource {
    suspend fun getAlbums(): List<AlbumModel>
}