package com.haidev.moviecatalogueapp.ui.favorite.tvshow

import android.app.Application
import androidx.lifecycle.LiveData
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.base.BaseViewModel

class TvShowFavoriteViewModel(private val apiRepository: ApiRepository, application: Application) :
    BaseViewModel<TvShowFavoriteNavigator>(application) {

    fun getAllFavoriteTvSHow(): LiveData<List<DetailTvShow.Response?>> {
        return apiRepository.getAllTvShowFavorite()
    }

}