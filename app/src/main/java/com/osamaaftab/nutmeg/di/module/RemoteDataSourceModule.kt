package com.osamaaftab.nutmeg.di.module

import com.osamaaftab.nutmeg.data.service.AlbumServices
import com.osamaaftab.nutmeg.data.service.PhotoServices
import com.osamaaftab.nutmeg.data.service.UserServices
import com.osamaaftab.nutmeg.data.source.remote.AlbumRemoteDataSource
import com.osamaaftab.nutmeg.data.source.remote.PhotoRemoteDataSource
import com.osamaaftab.nutmeg.data.source.remote.UserRemoteDataSource
import org.koin.dsl.module

val RemoteDataSourceModule = module {
    single { provideRemoteAlbumDataSource(get()) }
    single { provideRemoteUserDataSource(get()) }
    single { provideRemotePhotoDataSource(get()) }
}


fun provideRemoteAlbumDataSource(
    albumServices: AlbumServices
): AlbumRemoteDataSource {
    return AlbumRemoteDataSource(albumServices)
}

fun provideRemoteUserDataSource(
    userServices: UserServices
): UserRemoteDataSource {
    return UserRemoteDataSource(userServices)
}

fun provideRemotePhotoDataSource(
    photoServices: PhotoServices
): PhotoRemoteDataSource {
    return PhotoRemoteDataSource(photoServices)
}