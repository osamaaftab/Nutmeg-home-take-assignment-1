package com.osamaaftab.nutmeg.di.module

import com.osamaaftab.nutmeg.data.ErrorHandler
import org.koin.dsl.module

val AppModule = module {
    single { provideApiError() }
}

fun provideApiError(): ErrorHandler {
    return ErrorHandler()
}