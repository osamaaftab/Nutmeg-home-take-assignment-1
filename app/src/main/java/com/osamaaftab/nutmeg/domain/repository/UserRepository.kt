package com.osamaaftab.nutmeg.domain.repository

import com.osamaaftab.nutmeg.domain.model.UserModel

interface UserRepository {
    suspend fun getUser(userId: String): UserModel
}