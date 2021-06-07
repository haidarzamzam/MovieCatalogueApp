package com.haidev.moviecatalogueapp.di

import com.haidev.moviecatalogueapp.data.source.remote.provideApiBasic
import com.haidev.moviecatalogueapp.data.source.remote.provideCacheInterceptor
import com.haidev.moviecatalogueapp.data.source.remote.provideHttpLoggingInterceptor
import com.haidev.moviecatalogueapp.data.source.remote.provideMoshiConverter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val remoteModule = module {
    single { provideCacheInterceptor() }
    single { provideHttpLoggingInterceptor() }
    single { provideMoshiConverter() }
    single {
        provideApiBasic(
            get(named(BASE_URL)),
            get()
        )
    }
}
