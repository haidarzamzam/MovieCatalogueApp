package com.haidev.moviecatalogueapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.haidev.moviecatalogueapp.di.apiRepositoryModule
import com.haidev.moviecatalogueapp.di.remoteModule
import com.haidev.moviecatalogueapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: MyApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(
                listOf(
                    viewModelModule,
                    apiRepositoryModule,
                    remoteModule,
                )
            )
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}