package com.haidev.moviecatalogueapp.data.source.endpoint

import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/{id_movie}")
    fun getDetailMovie(
        @Path("id_movie") id_movie: String?,
        @Query("api_key") api_key: String?
    ): Deferred<DetailMovie.Response>

    @GET("tv/{id_tv}")
    fun getDetailTvShow(
        @Query("api_key") api_key: String?,
        @Path("id_tv") id_tv: String?
    ): Deferred<DetailTvShow.Response>
}