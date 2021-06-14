package com.haidev.moviecatalogueapp.di

import com.haidev.moviecatalogueapp.data.source.dao.MovieDao
import com.haidev.moviecatalogueapp.data.source.dao.TvShowDao
import com.haidev.moviecatalogueapp.data.source.local.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localModule = module {
    fun movieDao(database: AppDatabase): MovieDao = database.movieDao()
    fun tvShowDao(database: AppDatabase): TvShowDao = database.tvShowDao()

    single { AppDatabase.getDatabase(androidApplication()) }
    single { movieDao(get()) }
    single { tvShowDao(get()) }
}