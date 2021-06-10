package com.haidev.moviecatalogueapp.data.source.endpoint

import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getListMovie(
        @Query("api_key") api_key: String?
    ): Deferred<ListMovie.Response>

    @GET("tv/popular")
    fun getListTvShow(
        @Query("api_key") api_key: String?
    ): Deferred<ListTvShow.Response>
}