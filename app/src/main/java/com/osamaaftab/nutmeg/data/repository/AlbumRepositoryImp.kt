package com.osamaaftab.nutmeg.data.repository

import com.osamaaftab.nutmeg.data.source.remote.AlbumRemoteDataSource
import com.osamaaftab.nutmeg.domain.model.AlbumModel
import com.osamaaftab.nutmeg.domain.model.PhotoModel
import com.osamaaftab.nutmeg.domain.model.UserModel
import com.osamaaftab.nutmeg.domain.repository.AlbumRepository
import com.osamaaftab.nutmeg.domain.repository.PhotoRepository
import com.osamaaftab.nutmeg.domain.repository.UserRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class AlbumRepositoryImp(
    private val albumRemoteDataSource: AlbumRemoteDataSource,
    private val photoRepository: PhotoRepository,
    private val userRepository: UserRepository
) : AlbumRepository {

    private lateinit var user: Deferred<UserModel>
    private lateinit var photo: Deferred<PhotoModel>

    override suspend fun getAlbum(): Flow<AlbumModel> = flow {
        albumRemoteDataSource.getAlbums().map { albumModel ->
            mapUserAndPhotoObject(albumModel)
            emit(albumModel)
        }
    }

    private suspend fun mapUserAndPhotoObject(albumModel: AlbumModel) {
        withContext(Dispatchers.IO) {
            user = async {
                userRepository.getUser(albumModel.userId)
            }

            photo = async {
                photoRepository.getPhoto(albumModel.id)
            }
        }

        albumModel.apply {
            userName = user.await().userName
            thumbNail = photo.await().thumbnailUrl
            photoTitle = photo.await().title
        }
    }
}