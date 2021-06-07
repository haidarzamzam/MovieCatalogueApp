package com.haidev.moviecatalogueapp.di

import org.koin.core.qualifier.named
import org.koin.dsl.module

const val BASE_URL: String = "https://api.themoviedb.org/3/"

val apiModule = module {

    single(named(BASE_URL)) { getBaseUrl() }
}

external fun getBaseUrl(): String
