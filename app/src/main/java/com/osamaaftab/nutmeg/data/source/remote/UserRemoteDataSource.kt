package com.osamaaftab.nutmeg.data.source.remote

import com.osamaaftab.nutmeg.data.service.UserServices
import com.osamaaftab.nutmeg.data.source.base.UserDataSource
import com.osamaaftab.nutmeg.domain.model.UserModel

class UserRemoteDataSource(private val userServices: UserServices) : UserDataSource {

    override suspend fun getUser(albumId: String): UserModel {
        return userServices.getUsersAsync(albumId).await()
    }
}