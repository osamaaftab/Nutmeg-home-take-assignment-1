package com.osamaaftab.nutmeg.di.module

import com.osamaaftab.nutmeg.data.repository.AlbumRepositoryImp
import com.osamaaftab.nutmeg.data.repository.PhotoRepositoryImp
import com.osamaaftab.nutmeg.data.repository.UserRepositoryImp
import com.osamaaftab.nutmeg.data.source.remote.AlbumRemoteDataSource
import com.osamaaftab.nutmeg.data.source.remote.PhotoRemoteDataSource
import com.osamaaftab.nutmeg.data.source.remote.UserRemoteDataSource
import com.osamaaftab.nutmeg.domain.repository.AlbumRepository
import com.osamaaftab.nutmeg.domain.repository.PhotoRepository
import com.osamaaftab.nutmeg.domain.repository.UserRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single { provideAlbumRepository(get(), get(), get()) }
    single { provideUserRepository(get()) }
    single { providePhotoRepository(get()) }

}

fun provideAlbumRepository(
    albumRemoteDataSource: AlbumRemoteDataSource,
    photoRepository: PhotoRepository,
    userRepository: UserRepository
): AlbumRepository {
    return AlbumRepositoryImp(albumRemoteDataSource, photoRepository, userRepository)
}

fun provideUserRepository(
    userRemoteDataSource: UserRemoteDataSource
): UserRepository {
    return UserRepositoryImp(userRemoteDataSource)
}

fun providePhotoRepository(
    photoRemoteDataSource: PhotoRemoteDataSource
): PhotoRepository {
    return PhotoRepositoryImp(photoRemoteDataSource)
}