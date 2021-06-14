package com.haidev.moviecatalogueapp.data.source.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.haidev.moviecatalogueapp.data.model.DetailTvShow

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tv_show_table")
    fun findAllTvShowTable(): LiveData<List<DetailTvShow.Response>>
}