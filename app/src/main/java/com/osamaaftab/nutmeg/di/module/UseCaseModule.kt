package com.osamaaftab.nutmeg.di.module

import com.osamaaftab.nutmeg.data.ErrorHandler
import com.osamaaftab.nutmeg.domain.repository.AlbumRepository
import com.osamaaftab.nutmeg.domain.usecase.GetAlbumUseCase
import org.koin.dsl.module


val UseCaseModule = module {
    single { provideGetAlbumUseCase(get(), get()) }
}

private fun provideGetAlbumUseCase(
    questionRepository: AlbumRepository,
    errorHandler: ErrorHandler
): GetAlbumUseCase {
    return GetAlbumUseCase(questionRepository, errorHandler)
}