package com.osamaaftab.nutmeg.data.repository

import com.osamaaftab.nutmeg.data.source.remote.UserRemoteDataSource
import com.osamaaftab.nutmeg.domain.model.UserModel
import com.osamaaftab.nutmeg.domain.repository.UserRepository

class UserRepositoryImp(private val userRemoteDataSource: UserRemoteDataSource) : UserRepository {
    override suspend fun getUser(userId: String): UserModel {
        return userRemoteDataSource.getUser(userId)
    }
}