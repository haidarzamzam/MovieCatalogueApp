package com.haidev.moviecatalogueapp.data.source.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.data.model.ListMovie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    fun readAllMovie(): DataSource.Factory<Int, ListMovie.Response.Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllMovie(movie: List<ListMovie.Response.Result>?)

    @Query("DELETE FROM movie_table")
    fun clearMovie()

    @Query("SELECT * FROM movie_favorite_table")
    fun readAllMovieFavorite(): LiveData<List<DetailMovie.Response?>>

    @Query("SELECT * FROM movie_favorite_table WHERE id = :idMovie")
    fun readMovieFavorite(idMovie: Int): LiveData<DetailMovie.Response?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovieFavorite(movie: DetailMovie.Response?)

    @Delete
    fun deleteMovieFavorite(movie: DetailMovie.Response?)
}