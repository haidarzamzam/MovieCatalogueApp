package com.haidev.moviecatalogueapp.di

import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.splash.SplashViewModel
import com.haidev.moviecatalogueapp.utils.ContextProviders
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(androidApplication()) }
}

val apiRepositoryModule = module {
    single { ContextProviders.getInstance() }
    single { ApiRepository(get()) }
}


