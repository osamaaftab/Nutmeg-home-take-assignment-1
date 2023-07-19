package com.osamaaftab.nutmeg.data.source.base

import com.osamaaftab.nutmeg.domain.model.PhotoModel

interface PhotoDataSource {
    suspend fun getPhoto(albumId: String): List<PhotoModel>?
}