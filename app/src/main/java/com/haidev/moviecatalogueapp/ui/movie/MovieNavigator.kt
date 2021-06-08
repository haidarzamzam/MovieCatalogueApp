package com.haidev.moviecatalogueapp.ui.movie

import com.haidev.moviecatalogueapp.data.model.ListMovie

interface MovieNavigator {
    fun navigateToDetailMovie(data: ListMovie.Response.Result)
}