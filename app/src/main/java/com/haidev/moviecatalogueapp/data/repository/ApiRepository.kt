package com.haidev.moviecatalogueapp.data.repository

import com.haidev.moviecatalogueapp.BuildConfig
import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import com.haidev.moviecatalogueapp.data.source.dao.MovieDao
import com.haidev.moviecatalogueapp.data.source.endpoint.ApiService

class ApiRepository(
    private val apiService: ApiService,
    private val movieDao: MovieDao,
) {
    fun readMovie(idMovie: Int) = movieDao.readMovie(idMovie)
    fun insertMovie(movie: DetailMovie.Response) = movieDao.insertMovie(movie)

    suspend fun getListMovie(): ListMovie.Response {
        return apiService.getListMovie(BuildConfig.API_KEY).await()
    }

    suspend fun getListTvShow(): ListTvShow.Response {
        return apiService.getListTvShow(BuildConfig.API_KEY).await()
    }

    suspend fun getDetailMovie(idMovie: String): DetailMovie.Response {
        return apiService.getDetailMovie(idMovie, BuildConfig.API_KEY).await()
    }

    suspend fun getDetailTvShow(idTvShow: String): DetailTvShow.Response {
        return apiService.getDetailTvShow(idTvShow, BuildConfig.API_KEY).await()
    }
}