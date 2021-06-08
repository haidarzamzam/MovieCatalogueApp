package com.haidev.moviecatalogueapp.ui.movie

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.data.model.Resource
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.base.BaseViewModel
import com.haidev.moviecatalogueapp.utils.ErrorUtils
import kotlinx.coroutines.launch

class MovieViewModel(private val apiRepository: ApiRepository, application: Application) :
    BaseViewModel<MovieNavigator>(application) {
    private val _listMovie = MutableLiveData<Resource<ListMovie.Response>>()
    val dataListMovie: LiveData<Resource<ListMovie.Response>>
        get() = _listMovie

    fun getListMovieAsync() {
        viewModelScope.launch {
            _listMovie.postValue(Resource.loading(null))
            try {
                val response = apiRepository.getListMovie()
                _listMovie.postValue(Resource.success(response))
            } catch (t: Throwable) {
                _listMovie.postValue(Resource.error(ErrorUtils.getErrorThrowableMsg(t), null, t))
            }
        }
    }
}