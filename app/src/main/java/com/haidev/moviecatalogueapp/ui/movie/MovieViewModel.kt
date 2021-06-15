package com.haidev.moviecatalogueapp.ui.movie

import android.app.Application
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.base.BaseViewModel

class MovieViewModel(private val apiRepository: ApiRepository, application: Application) :
    BaseViewModel<MovieNavigator>(application) {

    fun getAllListMovie() = apiRepository.getListMovie()

    /* private val _listMovie = MutableLiveData<Resource<ListMovie.Response>>()
     val dataListMovie: MutableLiveData<Resource<ListMovie.Response>>
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
     }*/
}