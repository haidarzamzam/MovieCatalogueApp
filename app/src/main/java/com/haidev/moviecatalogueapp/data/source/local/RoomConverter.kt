package com.haidev.moviecatalogueapp.data.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.data.model.ListTvShow

class RoomConverter {
    //Movie Table
    @TypeConverter
    fun listMovieToJson(value: List<ListMovie.Response.Result>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToListMovie(value: String) =
        Gson().fromJson(value, Array<ListMovie.Response.Result>::class.java)
            .toList()

    //Tv Show Table
    @TypeConverter
    fun listTvShowToJson(value: List<ListTvShow.Response.Result>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToListTvShow(value: String) =
        Gson().fromJson(value, Array<ListTvShow.Response.Result>::class.java)
            .toList()

    //Detail Movie Table
    @TypeConverter
    fun movieGenreToJson(value: List<DetailMovie.Response.Genre>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToMovieGenre(value: String) =
        Gson().fromJson(value, Array<DetailMovie.Response.Genre>::class.java)
            .toList()

    @TypeConverter
    fun movieProductionCompanyToJson(value: List<DetailMovie.Response.ProductionCompany>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToMovieProductionCompany(value: String) =
        Gson().fromJson(value, Array<DetailMovie.Response.ProductionCompany>::class.java)
            .toList()

    @TypeConverter
    fun movieProductionCountryToJson(value: List<DetailMovie.Response.ProductionCountry>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToMovieProductionCountry(value: String) =
        Gson().fromJson(value, Array<DetailMovie.Response.ProductionCountry>::class.java)
            .toList()

    @TypeConverter
    fun movieSpokenLanguageToJson(value: List<DetailMovie.Response.SpokenLanguage>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToMovieSpokenLanguage(value: String) =
        Gson().fromJson(value, Array<DetailMovie.Response.SpokenLanguage>::class.java)
            .toList()

    @TypeConverter
    fun movieBelongsToCollectionToJson(value: DetailMovie.Response.BelongsToCollection?): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToMovieBelongsToCollection(value: String): DetailMovie.Response.BelongsToCollection? =
        Gson().fromJson(value, DetailMovie.Response.BelongsToCollection::class.java)

    //Detail Tv Show Table
    @TypeConverter
    fun tvShowCreatedByItemToJson(value: List<DetailTvShow.Response.CreatedByItem>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToTvCreatedByItem(value: String) =
        Gson().fromJson(value, Array<DetailTvShow.Response.CreatedByItem>::class.java)
            .toList()

    @TypeConverter
    fun tvShowGenreToJson(value: List<DetailTvShow.Response.Genre>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToTvGenre(value: String) =
        Gson().fromJson(value, Array<DetailTvShow.Response.Genre>::class.java)
            .toList()

    @TypeConverter
    fun tvShowNextEpisodeToAirToJson(value: DetailTvShow.Response.NextEpisodeToAir?): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToTvNextEpisodeToAir(value: String): DetailTvShow.Response.NextEpisodeToAir? =
        Gson().fromJson(value, DetailTvShow.Response.NextEpisodeToAir::class.java)

    @TypeConverter
    fun tvShowLastEpisodeToAirToJson(value: DetailTvShow.Response.LastEpisodeToAir?): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToTvLastEpisodeToAir(value: String): DetailTvShow.Response.LastEpisodeToAir? =
        Gson().fromJson(value, DetailTvShow.Response.LastEpisodeToAir::class.java)

    @TypeConverter
    fun tvShowNetworkToJson(value: List<DetailTvShow.Response.Network>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToTvNetwork(value: String) =
        Gson().fromJson(value, Array<DetailTvShow.Response.Network>::class.java)
            .toList()

    @TypeConverter
    fun tvShowProductionCompanyToJson(value: List<DetailTvShow.Response.ProductionCompany>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToTvProductionCompany(value: String) =
        Gson().fromJson(value, Array<DetailTvShow.Response.ProductionCompany>::class.java)
            .toList()

    @TypeConverter
    fun tvShowProductionCountryToJson(value: List<DetailTvShow.Response.ProductionCountry>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToTvProductionCountry(value: String) =
        Gson().fromJson(value, Array<DetailTvShow.Response.ProductionCountry>::class.java)
            .toList()

    @TypeConverter
    fun tvShowSeasonToJson(value: List<DetailTvShow.Response.Season>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToTvSeason(value: String) =
        Gson().fromJson(value, Array<DetailTvShow.Response.Season>::class.java)
            .toList()

    @TypeConverter
    fun tvShowSpokenLanguageToJson(value: List<DetailTvShow.Response.SpokenLanguage>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToTvSpokenLanguage(value: String) =
        Gson().fromJson(value, Array<DetailTvShow.Response.SpokenLanguage>::class.java)
            .toList()

    //General
    @TypeConverter
    fun integerToJson(value: List<Int>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToInteger(value: String) =
        Gson().fromJson(value, Array<Int>::class.java)
            .toList()

    @TypeConverter
    fun stringToJson(value: List<String>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToString(value: String) =
        Gson().fromJson(value, Array<String>::class.java)
            .toList()
}