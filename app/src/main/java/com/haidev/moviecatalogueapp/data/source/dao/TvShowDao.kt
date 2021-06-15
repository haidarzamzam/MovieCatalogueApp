package com.haidev.moviecatalogueapp.data.source.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.data.model.ListTvShow

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tv_show_table")
    fun readAllTvShow(): DataSource.Factory<Int, ListTvShow.Response.Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllTvShow(movie: List<ListTvShow.Response.Result>?)

    @Query("DELETE FROM tv_show_table")
    fun clearTvShow()

    @Query("SELECT * FROM tv_favorite_show_table")
    fun readAllTvShowFavorite(): LiveData<List<DetailTvShow.Response?>>

    @Query("SELECT * FROM tv_favorite_show_table WHERE id = :idTvShow")
    fun readTvShowFavorite(idTvShow: Int): LiveData<DetailTvShow.Response?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTvShowFavorite(tvShow: DetailTvShow.Response?)

    @Delete
    fun deleteTvShowFavorite(tvShow: DetailTvShow.Response?)
}