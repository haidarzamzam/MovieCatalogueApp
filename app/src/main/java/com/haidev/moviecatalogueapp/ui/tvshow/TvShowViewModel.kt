package com.haidev.moviecatalogueapp.ui.tvshow

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import com.haidev.moviecatalogueapp.data.model.Resource
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.base.BaseViewModel
import com.haidev.moviecatalogueapp.utils.ErrorUtils
import kotlinx.coroutines.launch

class TvShowViewModel(private val apiRepository: ApiRepository, application: Application) :
    BaseViewModel<TvShowNavigator>(application) {
    private val _listTvShow = MutableLiveData<Resource<ListTvShow.Response>>()
    val dataListTvShow: LiveData<Resource<ListTvShow.Response>>
        get() = _listTvShow

    fun getListTvShowAsync() {
        viewModelScope.launch {
            _listTvShow.postValue(Resource.loading(null))
            try {
                val response = apiRepository.getListTvShow()
                _listTvShow.postValue(Resource.success(response))
            } catch (t: Throwable) {
                _listTvShow.postValue(Resource.error(ErrorUtils.getErrorThrowableMsg(t), null, t))
            }
        }
    }
}