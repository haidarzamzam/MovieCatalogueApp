package com.haidev.moviecatalogueapp.ui.favorite.movie

import com.haidev.moviecatalogueapp.data.model.DetailMovie

interface MovieFavoriteNavigator {
    fun navigateToDetailMovie(data: DetailMovie.Response)
}