package com.haidev.moviecatalogueapp.data.source.remote

import com.haidev.moviecatalogueapp.data.source.endpoint.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun provideCacheInterceptor() = run {
    Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge =
            60 // read from cache for 60 seconds even if there is internet connection
        response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .removeHeader("Pragma")
            .build()
    }
}

fun provideHttpLoggingInterceptor() = run {
    HttpLoggingInterceptor().apply {
        apply { level = HttpLoggingInterceptor.Level.BODY }
    }
}

fun provideMoshiConverter(): Moshi = run {
    Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

fun provideApiBasic(
    baseUrl: String,
    moshiConverter: Moshi
): ApiService {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshiConverter))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(ApiService::class.java)
}

