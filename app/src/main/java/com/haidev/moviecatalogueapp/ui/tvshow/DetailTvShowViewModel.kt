package com.haidev.moviecatalogueapp.ui.tvshow

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.data.model.Resource
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.base.BaseViewModel
import com.haidev.moviecatalogueapp.utils.ErrorUtils
import kotlinx.coroutines.launch

class DetailTvShowViewModel(private val apiRepository: ApiRepository, application: Application) :
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
}