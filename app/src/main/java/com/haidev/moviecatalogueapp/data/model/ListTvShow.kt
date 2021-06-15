package com.haidev.moviecatalogueapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

object ListTvShow {
    @Parcelize
    data class Response(
        val page: Int,
        val results: List<Result>,
        val total_pages: Int,
        val total_results: Int
    ) : Parcelable {
        @Entity(tableName = "tv_show_table")
        @Parcelize
        data class Result(
            val backdrop_path: String,
            val first_air_date: String,
            val genre_ids: List<Int>,
            @PrimaryKey
            val id: Int,
            val name: String,
            val origin_country: List<String>,
            val original_language: String,
            val original_name: String,
            val overview: String,
            val popularity: Double,
            val poster_path: String,
            val vote_average: Double,
            val vote_count: Int
        ) : Parcelable
    }


}