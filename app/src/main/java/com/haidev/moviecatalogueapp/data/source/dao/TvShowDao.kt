package com.haidev.moviecatalogueapp.data.source.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.haidev.moviecatalogueapp.data.model.ListTvShow

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tv_show_table")
    fun readAllTvShow(): DataSource.Factory<Int, ListTvShow.Response.Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllTvShow(movie: List<ListTvShow.Response.Result>?)

    @Query("DELETE FROM tv_show_table")
    fun clearTvShow()
}