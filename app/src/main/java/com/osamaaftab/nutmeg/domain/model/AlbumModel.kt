package com.osamaaftab.nutmeg.domain.model

data class AlbumModel(
    val userId: String,
    val title: String,
    val id: String,
    var userName: String?,
    var thumbNail: String?,
    var photoTitle: String?
)