package com.osamaaftab.nutmeg.di.module

import com.osamaaftab.nutmeg.presentation.viewmodel.AlbumViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ActivityModule = module {
    viewModel { AlbumViewModel(get()) }
}