package com.haidev.moviecatalogueapp.data.source.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.haidev.moviecatalogueapp.data.model.DetailMovie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    fun readAllMovie(): LiveData<List<DetailMovie.Response>>

    @Query("SELECT * FROM movie_table WHERE id = :idMovie")
    fun readMovie(idMovie: Int): LiveData<DetailMovie.Response?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(module: DetailMovie.Response)
}