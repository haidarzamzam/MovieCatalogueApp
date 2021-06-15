package com.haidev.moviecatalogueapp.ui.movie

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.data.model.Resource
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.base.BaseViewModel
import com.haidev.moviecatalogueapp.utils.ErrorUtils
import kotlinx.coroutines.launch

class DetailMovieViewModel(
    private val apiRepository: ApiRepository,
    application: Application,
) :
    BaseViewModel<DetailMovieNavigator>(application) {
    private val _detailMovie = MutableLiveData<Resource<DetailMovie.Response>>()
    val dataDetailMovie: MutableLiveData<Resource<DetailMovie.Response>>
        get() = _detailMovie

    /*fun getFavoriteMovie(idMovie: Int): LiveData<DetailMovie.Response?> {
        return apiRepository.readMovie(idMovie)
    }*/

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

    /*fun setFavoriteMovie(movie: DetailMovie.Response) {
        appExecutors.diskIO().execute { apiRepository.insertMovie(movie) }
    }

    fun deleteFavoriteMovie(movie: DetailMovie.Response) {
        appExecutors.diskIO().execute { apiRepository.deleteMovie(movie) }
    }*/
}