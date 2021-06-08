package com.haidev.moviecatalogueapp.ui.movie

import android.app.Application
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.base.BaseViewModel

class DetailMovieViewModel(private val apiRepository: ApiRepository, application: Application) :
    BaseViewModel<DetailMovieNavigator>(application)