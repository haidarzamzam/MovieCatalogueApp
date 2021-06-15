package com.haidev.moviecatalogueapp.ui.tvshow

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.data.model.Resource
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.base.BaseViewModel
import com.haidev.moviecatalogueapp.utils.ContextProviders
import com.haidev.moviecatalogueapp.utils.ErrorUtils
import com.haidev.moviecatalogueapp.utils.launchOn
import kotlinx.coroutines.launch

class DetailTvShowViewModel(
    private val apiRepository: ApiRepository,
    application: Application,
    private val coroutineContext: ContextProviders
) :
    BaseViewModel<DetailTvShowNavigator>(application) {
    private val _detailTvShow = MutableLiveData<Resource<DetailTvShow.Response>>()
    val dataDetailTvShow: MutableLiveData<Resource<DetailTvShow.Response>>
        get() = _detailTvShow

    fun getDetailTvShowAsync(idTv: String) {
        viewModelScope.launch {
            _detailTvShow.postValue(Resource.loading(null))
            try {
                val response = apiRepository.getDetailTvShow(idTv)
                _detailTvShow.postValue(Resource.success(response))
            } catch (t: Throwable) {
                _detailTvShow.postValue(Resource.error(ErrorUtils.getErrorThrowableMsg(t), null, t))
            }
        }
    }

    fun getFavoriteTvSHow(idTvShow: Int): LiveData<DetailTvShow.Response?> {
        return apiRepository.getTvShowFavorite(idTvShow)
    }

    fun addFavoriteTvSHow(tvShow: DetailTvShow.Response) {
        launchOn(coroutineContext.IO) {
            apiRepository.addTvShowFavorite(tvShow)
        }
    }

    fun deleteFavoriteTvSHow(tvShow: DetailTvShow.Response) {
        launchOn(coroutineContext.IO) {
            apiRepository.deleteTvShowFavorite(tvShow)
        }
    }
}