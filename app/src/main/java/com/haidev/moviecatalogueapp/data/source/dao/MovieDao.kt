package com.haidev.moviecatalogueapp.data.source.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.haidev.moviecatalogueapp.data.model.ListMovie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    fun readAllMovie(): DataSource.Factory<Int, ListMovie.Response.Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllMovie(movie: List<ListMovie.Response.Result>?)

    @Query("DELETE FROM movie_table")
    fun clearMovie()
}