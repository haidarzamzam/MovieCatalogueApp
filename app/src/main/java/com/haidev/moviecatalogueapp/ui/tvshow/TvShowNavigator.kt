package com.haidev.moviecatalogueapp.ui.tvshow

import com.haidev.moviecatalogueapp.data.model.ListTvShow

interface TvShowNavigator {
    fun navigateToDetailTvShow(data: ListTvShow.Response.Result)
}