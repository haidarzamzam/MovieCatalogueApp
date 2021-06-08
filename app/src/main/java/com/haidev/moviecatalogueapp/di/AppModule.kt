package com.haidev.moviecatalogueapp.di

import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.home.HomeViewModel
import com.haidev.moviecatalogueapp.ui.movie.MovieViewModel
import com.haidev.moviecatalogueapp.ui.splash.SplashViewModel
import com.haidev.moviecatalogueapp.ui.tvshow.TvShowViewModel
import com.haidev.moviecatalogueapp.utils.ContextProviders
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(androidApplication()) }
    viewModel { HomeViewModel(androidApplication()) }
    viewModel { MovieViewModel(get(), androidApplication()) }
    viewModel { TvShowViewModel(get(), androidApplication()) }
}

val apiRepositoryModule = module {
    single { ContextProviders.getInstance() }
    single { ApiRepository(get()) }
}


