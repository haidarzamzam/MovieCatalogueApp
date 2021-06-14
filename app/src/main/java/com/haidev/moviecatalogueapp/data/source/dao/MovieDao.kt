package com.haidev.moviecatalogueapp.data.source.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.haidev.moviecatalogueapp.data.model.DetailMovie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    fun findAllMovieTable(): LiveData<List<DetailMovie.Response>>
}