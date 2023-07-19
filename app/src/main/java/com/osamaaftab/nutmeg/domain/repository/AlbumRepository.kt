package com.osamaaftab.nutmeg.domain.repository

import com.osamaaftab.nutmeg.domain.model.AlbumModel
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    suspend fun getAlbum(): Flow<AlbumModel>
}