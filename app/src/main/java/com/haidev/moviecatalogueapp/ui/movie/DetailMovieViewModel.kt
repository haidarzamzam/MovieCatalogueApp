package com.haidev.moviecatalogueapp.ui.movie

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.data.model.Resource
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.base.BaseViewModel
import com.haidev.moviecatalogueapp.utils.ContextProviders
import com.haidev.moviecatalogueapp.utils.ErrorUtils
import com.haidev.moviecatalogueapp.utils.launchOn
import kotlinx.coroutines.launch

class DetailMovieViewModel(
    private val apiRepository: ApiRepository,
    application: Application,
    private val coroutineContext: ContextProviders
) :
    BaseViewModel<DetailMovieNavigator>(application) {
    private val _detailMovie = MutableLiveData<Resource<DetailMovie.Response>>()
    val dataDetailMovie: MutableLiveData<Resource<DetailMovie.Response>>
        get() = _detailMovie

    fun getDetailMovieAsync(idMovie: String) {
        viewModelScope.launch {
            _detailMovie.postValue(Resource.loading(null))
            try {
                val response = apiRepository.getDetailMovie(idMovie)
                _detailMovie.postValue(Resource.success(response))
            } catch (t: Throwable) {
                _detailMovie.postValue(Resource.error(ErrorUtils.getErrorThrowableMsg(t), null, t))
            }
        }
    }

    fun getFavoriteMovie(idMovie: Int): LiveData<DetailMovie.Response?> {
        return apiRepository.getMovieFavorite(idMovie)
    }

    fun addFavoriteMovie(movie: DetailMovie.Response?) {
        launchOn(coroutineContext.IO) {
            apiRepository.addMovieFavorite(movie)
        }
    }

    fun deleteFavoriteMovie(movie: DetailMovie.Response?) {
        launchOn(coroutineContext.IO) {
            apiRepository.deleteMovieFavorite(movie)
        }
    }
}