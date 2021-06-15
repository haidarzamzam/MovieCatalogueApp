package com.haidev.moviecatalogueapp.ui.favorite.tvshow

import com.haidev.moviecatalogueapp.data.model.DetailTvShow

interface TvShowFavoriteNavigator {
    fun navigateToDetailTvShow(data: DetailTvShow.Response)
}