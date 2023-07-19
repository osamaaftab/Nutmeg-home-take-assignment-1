package com.osamaaftab.nutmeg.data.source.base

import com.osamaaftab.nutmeg.domain.model.UserModel

interface UserDataSource {
    suspend fun getUser(albumId: String): UserModel
}