package com.haidev.moviecatalogueapp.di

import com.haidev.moviecatalogueapp.BuildConfig
import com.haidev.moviecatalogueapp.data.source.remote.provideApiBasic
import com.haidev.moviecatalogueapp.data.source.remote.provideCacheInterceptor
import com.haidev.moviecatalogueapp.data.source.remote.provideHttpLoggingInterceptor
import com.haidev.moviecatalogueapp.data.source.remote.provideMoshiConverter
import org.koin.dsl.module

val remoteModule = module {
    single { provideCacheInterceptor() }
    single { provideHttpLoggingInterceptor() }
    single { provideMoshiConverter() }
    single {
        provideApiBasic(
            BuildConfig.API_URL,
            get()
        )
    }
}
