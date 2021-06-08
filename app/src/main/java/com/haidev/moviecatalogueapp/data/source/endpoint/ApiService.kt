package com.haidev.moviecatalogueapp.data.source.endpoint

import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {
    @GET("movie/popular?api_key=3d31335928249bca6a9f04d1d9d85d03")
    fun getListMovie(): Deferred<ListMovie.Response>

    @GET("tv/popular?api_key=3d31335928249bca6a9f04d1d9d85d03")
    fun getListTvShow(): Deferred<ListTvShow.Response>
}