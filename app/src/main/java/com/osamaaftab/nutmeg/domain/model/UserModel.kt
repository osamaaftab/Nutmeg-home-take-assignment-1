package com.osamaaftab.nutmeg.domain.model

import com.squareup.moshi.Json

data class UserModel(
    val id: String,
    @field:Json(name = "username")
    val userName: String
)