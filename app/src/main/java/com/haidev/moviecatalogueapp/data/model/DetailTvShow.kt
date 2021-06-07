package com.haidev.moviecatalogueapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

object DetailTvShow {
    @Parcelize
    data class Response(
        val backdrop_path: String?,
        val created_by: List<CreatedBy>,
        val episode_run_time: List<Int>,
        val first_air_date: String,
        val genres: List<Genre>,
        val homepage: String,
        val id: Int,
        val in_production: Boolean,
        val languages: List<String>,
        val last_air_date: String,
        val last_episode_to_air: LastEpisodeToAir,
        val name: String,
        val networks: List<Network>,
        val next_episode_to_air: String?,
        val number_of_episodes: Int,
        val number_of_seasons: Int,
        val origin_country: List<String>,
        val original_language: String,
        val original_name: String,
        val overview: String,
        val popularity: Double,
        val poster_path: String,
        val production_companies: List<String?>,
        val production_countries: List<String?>,
        val seasons: List<Season>,
        val spoken_languages: List<SpokenLanguage>,
        val status: String,
        val tagline: String,
        val type: String,
        val vote_average: Double,
        val vote_count: Int
    ) : Parcelable {
        @Parcelize
        data class CreatedBy(
            val credit_id: String,
            val id: Int,
            val name: String,
            val profile_path: String?
        ) : Parcelable

        @Parcelize
        data class Genre(
            val id: Int,
            val name: String
        ) : Parcelable

        @Parcelize
        data class LastEpisodeToAir(
            val air_date: String,
            val episode_number: Int,
            val id: Int,
            val name: String,
            val overview: String,
            val production_code: String,
            val season_number: Int,
            val still_path: String?,
            val vote_average: Int,
            val vote_count: Int
        ) : Parcelable

        @Parcelize
        data class Network(
            val id: Int,
            val logo_path: String,
            val name: String,
            val origin_country: String
        ) : Parcelable

        @Parcelize
        data class Season(
            val air_date: String,
            val episode_count: Int,
            val id: Int,
            val name: String,
            val overview: String,
            val poster_path: String?,
            val season_number: Int
        ) : Parcelable

        @Parcelize
        data class SpokenLanguage(
            val english_name: String,
            val iso_639_1: String,
            val name: String
        ) : Parcelable
    }

}