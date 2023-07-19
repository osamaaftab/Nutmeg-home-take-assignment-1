package com.osamaaftab.nutmeg.domain.usecase

import com.osamaaftab.nutmeg.data.ErrorHandler
import com.osamaaftab.nutmeg.domain.model.AlbumModel
import com.osamaaftab.nutmeg.domain.repository.AlbumRepository
import com.osamaaftab.nutmeg.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow

class GetAlbumUseCase constructor(
    private val albumRepository: AlbumRepository,
    errorHandler: ErrorHandler?
) : UseCase<Flow<AlbumModel>, Unit>(errorHandler) {

    override suspend fun run(params: Unit?): Flow<AlbumModel> {
        return albumRepository.getAlbum()
    }
}
