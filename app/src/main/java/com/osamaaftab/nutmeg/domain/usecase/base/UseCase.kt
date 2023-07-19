package com.osamaaftab.nutmeg.domain.usecase.base

import com.osamaaftab.nutmeg.data.ErrorHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class UseCase<ReturnType, in Params>(private val errorHandler: ErrorHandler?) where ReturnType : Any {

    abstract suspend fun run(params: Params? = null): ReturnType

    fun invoke(scope: CoroutineScope, params: Params?, onResult: UseCaseResponse<ReturnType>) {
        scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    run(params)
                }.let {
                    onResult.onSuccess(it)
                }
            } catch (e: Throwable) {
                onResult.onError(errorHandler?.traceErrorException(e))
            }
        }
    }
}