package com.osamaaftab.nutmeg.domain.repository

import com.osamaaftab.nutmeg.domain.model.PhotoModel

interface PhotoRepository {
    suspend fun getPhoto(albumId: String): PhotoModel
}