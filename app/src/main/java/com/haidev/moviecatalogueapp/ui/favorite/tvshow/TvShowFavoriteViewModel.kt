package com.haidev.moviecatalogueapp.ui.favorite.tvshow

import android.app.Application
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.base.BaseViewModel

class TvShowFavoriteViewModel(private val apiRepository: ApiRepository, application: Application) :
    BaseViewModel<TvShowFavoriteNavigator>(application)