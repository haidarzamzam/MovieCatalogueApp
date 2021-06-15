package com.haidev.moviecatalogueapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

object ListMovie {
    data class Response(
        val page: Int,
        val results: List<Result>,
        val total_pages: Int,
        val total_results: Int
    ) {
        @Entity(tableName = "movie_table")
        @Parcelize
        data class Result(
            val adult: Boolean,
            val backdrop_path: String,
            val genre_ids: List<Int>,
            @PrimaryKey
            val id: Int,
            val original_language: String,
            val original_title: String,
            val overview: String,
            val popularity: Double,
            val poster_path: String,
            val release_date: String,
            val title: String,
            val video: Boolean,
            val vote_average: Double,
            val vote_count: Int
        ) : Parcelable
    }


}