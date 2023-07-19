package com.osamaaftab.nutmeg.presentation.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.osamaaftab.nutmeg.domain.model.AlbumModel
import com.osamaaftab.nutmeg.domain.model.ErrorModel
import com.osamaaftab.nutmeg.domain.usecase.GetAlbumUseCase
import com.osamaaftab.nutmeg.domain.usecase.base.UseCaseResponse
import com.osamaaftab.nutmeg.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class AlbumViewModel(private val getAlbumUseCase: GetAlbumUseCase) : BaseViewModel() {

    private val onProgressShowLiveData = MutableLiveData<Boolean>()

    private val onErrorShowLiveData = MutableLiveData<Boolean>()

    private val albumListLiveData = MutableLiveData<List<AlbumModel>>()

    private val albumList = mutableListOf<AlbumModel>()

    init {
        loadAlbumList()
    }

    fun getAlbumUseCaseResponse() = object : UseCaseResponse<Flow<AlbumModel>> {

        override suspend fun onSuccess(result: Flow<AlbumModel>) {
            result.collect { album ->
                Log.i(ContentValues.TAG, "result: $album")
                albumList.add(album)
                onProgressShowLiveData.postValue(false)
                onErrorShowLiveData.postValue(false)
                albumListLiveData.postValue(albumList)
            }
        }
        override fun onError(errorModel: ErrorModel?) {
            Log.i(ContentValues.TAG, "error status: ${errorModel?.errorStatus}")
            Log.i(ContentValues.TAG, "error message: ${errorModel?.message}")
            onProgressShowLiveData.postValue(false)
            onErrorShowLiveData.postValue(true)
        }

    }

    private fun loadAlbumList() {
        onProgressShowLiveData.postValue(true)
        getAlbumUseCase.invoke(scope, null, getAlbumUseCaseResponse())
    }

    fun getShowProgressLiveData(): LiveData<Boolean> {
        return onProgressShowLiveData
    }

    fun getShowErrorLiveData(): LiveData<Boolean> {
        return onErrorShowLiveData
    }

    fun getAlbumListLiveData(): LiveData<List<AlbumModel>> {
        return albumListLiveData
    }
}

