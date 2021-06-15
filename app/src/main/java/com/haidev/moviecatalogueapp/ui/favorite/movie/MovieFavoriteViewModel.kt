package com.haidev.moviecatalogueapp.ui.favorite.movie

import android.app.Application
import androidx.lifecycle.LiveData
import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.base.BaseViewModel

class MovieFavoriteViewModel(private val apiRepository: ApiRepository, application: Application) :
    BaseViewModel<MovieFavoriteNavigator>(application) {

    fun getAllFavoriteMovie(): LiveData<List<DetailMovie.Response?>> {
        return apiRepository.getAllMovieFavorite()
    }
}