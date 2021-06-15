package com.haidev.moviecatalogueapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.haidev.moviecatalogueapp.BuildConfig
import com.haidev.moviecatalogueapp.data.model.*
import com.haidev.moviecatalogueapp.data.source.dao.MovieDao
import com.haidev.moviecatalogueapp.data.source.dao.TvShowDao
import com.haidev.moviecatalogueapp.data.source.endpoint.ApiService
import com.haidev.moviecatalogueapp.data.source.utils.ApiResponse
import com.haidev.moviecatalogueapp.data.source.utils.NetworkBoundResource
import com.haidev.moviecatalogueapp.utils.ContextProviders
import com.haidev.moviecatalogueapp.utils.ErrorUtils.getServiceErrorMsg
import com.haidev.moviecatalogueapp.utils.launchOn
import com.haidev.moviecatalogueapp.utils.safeApiCall

class ApiRepository(
    private val apiService: ApiService,
    private val movieDao: MovieDao,
    private val tvShowDao: TvShowDao,
    private val coroutineContext: ContextProviders
) {
    fun getAllMovieFavorite() = movieDao.readAllMovieFavorite()
    fun getMovieFavorite(idMovie: Int) = movieDao.readMovieFavorite(idMovie)
    fun addMovieFavorite(movie: DetailMovie.Response) = movieDao.addMovieFavorite(movie)
    fun deleteMovieFavorite(movie: DetailMovie.Response) = movieDao.deleteMovieFavorite(movie)

    fun getAllTvShowFavorite() = tvShowDao.readAllTvShowFavorite()
    fun getTvShowFavorite(idTvShow: Int) = tvShowDao.readTvShowFavorite(idTvShow)
    fun addTvShowFavorite(tvShow: DetailTvShow.Response) = tvShowDao.addTvShowFavorite(tvShow)
    fun deleteTvShowFavorite(tvShow: DetailTvShow.Response) = tvShowDao.deleteTvShowFavorite(tvShow)

    fun getListMovie(): LiveData<Resource<PagedList<ListMovie.Response.Result>>> {
        return object :
            NetworkBoundResource<PagedList<ListMovie.Response.Result>, List<ListMovie.Response.Result>>(
                coroutineContext
            ) {

            override fun loadFromDB(): LiveData<PagedList<ListMovie.Response.Result>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(movieDao.readAllMovie(), config).build()
            }

            override fun shouldFetch(data: PagedList<ListMovie.Response.Result>?) = true

            override fun createCall(): LiveData<ApiResponse<List<ListMovie.Response.Result>>> {
                val result = MutableLiveData<ApiResponse<List<ListMovie.Response.Result>>>()
                launchOn(coroutineContext.IO) {
                    safeApiCall({
                        apiService.getListMovie(BuildConfig.API_KEY).await().also {
                            result.postValue(ApiResponse.success(it.results))
                        }
                    }, {
                        result.postValue(ApiResponse.error(getServiceErrorMsg(it), null))
                    })
                }
                return result
            }

            override fun saveCallResult(data: List<ListMovie.Response.Result>) {
                movieDao.clearMovie()
                movieDao.addAllMovie(data)
            }
        }.asLiveData()
    }

    fun getListTvShow(): LiveData<Resource<PagedList<ListTvShow.Response.Result>>> {
        return object :
            NetworkBoundResource<PagedList<ListTvShow.Response.Result>, List<ListTvShow.Response.Result>>(
                coroutineContext
            ) {

            override fun loadFromDB(): LiveData<PagedList<ListTvShow.Response.Result>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(tvShowDao.readAllTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<ListTvShow.Response.Result>?) = true

            override fun createCall(): LiveData<ApiResponse<List<ListTvShow.Response.Result>>> {
                val result = MutableLiveData<ApiResponse<List<ListTvShow.Response.Result>>>()
                launchOn(coroutineContext.IO) {
                    safeApiCall({
                        apiService.getListTvShow(BuildConfig.API_KEY).await().also {
                            result.postValue(ApiResponse.success(it.results))
                        }
                    }, {
                        result.postValue(ApiResponse.error(getServiceErrorMsg(it), null))
                    })
                }
                return result
            }

            override fun saveCallResult(data: List<ListTvShow.Response.Result>) {
                tvShowDao.clearTvShow()
                tvShowDao.addAllTvShow(data)
            }
        }.asLiveData()
    }

    suspend fun getDetailMovie(idMovie: String): DetailMovie.Response {
        return apiService.getDetailMovie(idMovie, BuildConfig.API_KEY).await()
    }

    suspend fun getDetailTvShow(idTvShow: String): DetailTvShow.Response {
        return apiService.getDetailTvShow(idTvShow, BuildConfig.API_KEY).await()
    }
}