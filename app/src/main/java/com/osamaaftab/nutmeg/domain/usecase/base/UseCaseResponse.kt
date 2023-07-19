package com.osamaaftab.nutmeg.domain.usecase.base

import com.osamaaftab.nutmeg.domain.model.ErrorModel

interface UseCaseResponse<in Type> {
    suspend fun onSuccess(result: Type)
    fun onError(errorModel: ErrorModel?)
}