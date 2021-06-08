package com.haidev.moviecatalogueapp.data.repository

import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import com.haidev.moviecatalogueapp.data.source.endpoint.ApiService

class ApiRepository(
    private val apiService: ApiService
) {
    suspend fun getListMovie(): ListMovie.Response {
        return apiService.getListMovie().await()
    }

    suspend fun getListTvShow(): ListTvShow.Response {
        return apiService.getListTvShow().await()
    }


}