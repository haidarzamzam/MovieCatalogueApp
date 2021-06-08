package com.haidev.moviecatalogueapp.data.repository

import com.haidev.moviecatalogueapp.BuildConfig
import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import com.haidev.moviecatalogueapp.data.source.endpoint.ApiService

class ApiRepository(
    private val apiService: ApiService
) {
    suspend fun getListMovie(): ListMovie.Response {
        return apiService.getListMovie(BuildConfig.API_KEY).await()
    }

    suspend fun getListTvShow(): ListTvShow.Response {
        return apiService.getListTvShow(BuildConfig.API_KEY).await()
    }

    suspend fun getDetailMovie(idMovie: String): DetailMovie.Response {
        return apiService.getDetailMovie(BuildConfig.API_KEY, idMovie).await()
    }

    suspend fun getDetailTvShow(idTv: String): DetailTvShow.Response {
        return apiService.getDetailTvShow(BuildConfig.API_KEY, idTv).await()
    }
}