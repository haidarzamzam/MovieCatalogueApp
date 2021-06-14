package com.haidev.moviecatalogueapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

object DetailTvShow {
    @Entity(tableName = "tv_show_table")
    @Parcelize
    data class Response(
        val backdrop_path: String?,
        val created_by: List<CreatedByItem>?,
        val episode_run_time: List<Int>?,
        val first_air_date: String?,
        val genres: List<Genre>?,
        val homepage: String?,
        @PrimaryKey
        val id: Int?,
        val in_production: Boolean?,
        val languages: List<String>?,
        val last_air_date: String?,
        val last_episode_to_air: LastEpisodeToAir? = null,
        val name: String?,
        val networks: List<Network>?,
        val next_episode_to_air: NextEpisodeToAir? = null,
        val number_of_episodes: Int?,
        val number_of_seasons: Int?,
        val origin_country: List<String>?,
        val original_language: String?,
        val original_name: String?,
        val overview: String?,
        val popularity: Double?,
        val poster_path: String?,
        val production_companies: List<ProductionCompany>?,
        val production_countries: List<ProductionCountry>?,
        val seasons: List<Season>?,
        val spoken_languages: List<SpokenLanguage>?,
        val status: String?,
        val tagline: String?,
        val type: String?,
        val vote_average: Double?,
        val vote_count: Int?
    ) : Parcelable {
        @Parcelize
        data class CreatedByItem(
            val credit_id: String?,
            val gender: Int?,
            val id: Int?,
            val name: String?,
            val profile_path: String?
        ) : Parcelable

        @Parcelize
        data class Genre(
            val id: Int?,
            val name: String?
        ) : Parcelable

        @Parcelize
        data class NextEpisodeToAir(
            val air_date: String? = "",
            val episode_number: Int? = 0,
            val id: Int? = 0,
            val name: String? = "",
            val overview: String? = "",
            val production_code: String? = "",
            val season_number: Int? = 0,
            val still_path: String? = "",
            val vote_average: Double? = 0.0,
            val vote_count: Int? = 0,
        ) : Parcelable

        @Parcelize
        data class LastEpisodeToAir(
            val air_date: String?,
            val episode_number: Int?,
            val id: Int?,
            val name: String?,
            val overview: String?,
            val production_code: String?,
            val season_number: Int?,
            val still_path: String?,
            val vote_average: Double?,
            val vote_count: Int?
        ) : Parcelable

        @Parcelize
        data class Network(
            val id: Int?,
            val logo_path: String?,
            val name: String?,
            val origin_country: String?
        ) : Parcelable

        @Parcelize
        data class ProductionCompany(
            val id: Int?,
            val logo_path: String?,
            val name: String?,
            val origin_country: String?
        ) : Parcelable

        @Parcelize
        data class ProductionCountry(
            val name: String?,
            val iso_3166_1: String?
        ) : Parcelable

        @Parcelize
        data class Season(
            val air_date: String?,
            val episode_count: Int?,
            val id: Int?,
            val name: String?,
            val overview: String?,
            val poster_path: String?,
            val season_number: Int?
        ) : Parcelable

        @Parcelize
        data class SpokenLanguage(
            val english_name: String?,
            val iso_639_1: String?,
            val name: String?
        ) : Parcelable
    }
}