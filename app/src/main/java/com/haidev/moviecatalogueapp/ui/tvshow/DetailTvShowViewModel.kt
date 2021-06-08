package com.haidev.moviecatalogueapp.ui.tvshow

import android.app.Application
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.base.BaseViewModel

class DetailTvShowViewModel(private val apiRepository: ApiRepository, application: Application) :
    BaseViewModel<DetailTvShowNavigator>(application)