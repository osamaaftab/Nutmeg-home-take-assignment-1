package com.osamaaftab.nutmeg.di.module

import com.osamaaftab.nutmeg.data.service.AlbumServices
import com.osamaaftab.nutmeg.data.service.PhotoServices
import com.osamaaftab.nutmeg.data.service.UserServices
import org.koin.dsl.module
import retrofit2.Retrofit

val ApiServicesModule = module {
    single { provideAlbumService(get()) }
    single { provideUsersService(get()) }
    single { providePhotosService(get()) }
}

private fun provideAlbumService(retrofit: Retrofit): AlbumServices {
    return retrofit.create(AlbumServices::class.java)
}

private fun provideUsersService(retrofit: Retrofit): UserServices {
    return retrofit.create(UserServices::class.java)
}

private fun providePhotosService(retrofit: Retrofit): PhotoServices {
    return retrofit.create(PhotoServices::class.java)
}
