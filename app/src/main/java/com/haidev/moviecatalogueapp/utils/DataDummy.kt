package com.haidev.moviecatalogueapp.utils

import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.data.model.ListTvShow

object DataDummy {
    fun generateDummyListMovie(): List<ListMovie.Response.Result> {

        val movies = ArrayList<ListMovie.Response.Result>()
        movies.add(
            ListMovie.Response.Result(
                false,
                "/57Jl9wB8Fv37S06z9mwfyoSpHof.jpg",
                arrayListOf(35, 85),
                337404,
                "en",
                "Cruella",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                6683.453,
                "/A0knvX7rlwTyZSKj8H5NiARb45.jpg",
                "2021-05-26",
                "Cruella",
                false,
                8.7,
                2203
            )
        )

        return movies
    }

    fun generateDummyListTvShow(): List<ListTvShow.Response.Result> {

        val tvshows = ArrayList<ListTvShow.Response.Result>()
        tvshows.add(
            ListTvShow.Response.Result(
                "/h48Dpb7ljv8WQvVdyFWVLz64h4G.jpg",
                "2016-01-25",
                arrayListOf(80, 10765),
                63174,
                "Lucifer",
                arrayListOf("US"),
                "en",
                "Lucifer",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                1464.514,
                "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                8.5,
                9132
            )
        )

        return tvshows
    }

    fun generateDummyDetailMovie(): List<DetailMovie.Response> {
        val belongsTo =
            DetailMovie.Response.BelongsToCollection(837007, "Cruella Collection", "", "")
        val genres = listOf(
            DetailMovie.Response.Genre(35, "Comedy"),
            DetailMovie.Response.Genre(80, "Crime")
        )
        val productionCompanies = listOf(
            DetailMovie.Response.ProductionCompany(
                2,
                "/wdrCwmRnLFJhEoH8GSfymY85KHT.png",
                "Walt Disney Pictures",
                "US"
            )
        )
        val productionCountry =
            listOf(DetailMovie.Response.ProductionCountry("US", "United States of America"))
        val spokenLanguage = listOf(DetailMovie.Response.SpokenLanguage("English", "en", "English"))

        val detailMovieShow = ArrayList<DetailMovie.Response>()

        detailMovieShow.add(
            DetailMovie.Response(
                false,
                "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
                belongsTo,
                200000000,
                genres,
                "https://movies.disney.com/cruella",
                337404,
                "tt3228774",
                "en",
                "Cruella",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                5507.395,
                "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                productionCompanies,
                productionCountry,
                "2021-05-26",
                88197497,
                134,
                spokenLanguage,
                "Released",
                "Hello Cruel World",
                "Cruella",
                false,
                8.7,
                2370
            )
        )

        return detailMovieShow
    }

    fun generateDummyDetailTvShow(): List<DetailTvShow.Response> {
        val createdBy = listOf(
            DetailTvShow.Response.CreatedByItem(
                "55fdc50ec3a368132a001852",
                2,
                1222585,
                "Tom Kapinos",
                "/ol7GfeO0OIDCWGYzlg1LDLmwluO.jpg"
            )
        )
        val episodeRunTime = listOf(45)
        val genres = listOf(
            DetailTvShow.Response.Genre(80, "Crime"),
            DetailTvShow.Response.Genre(10765, "Sci-Fi & Fantasy")
        )
        val languages = listOf("en")
        val lastEpisodeToAir = DetailTvShow.Response.LastEpisodeToAir(
            "2021-05-28",
            16,
            2856945,
            "A Chance at a Happy Ending",
            "The end is nigh! Lucifer, Chloe, Maze and Amenadiel prepare for battle with Michael and his not-so-angelic army of supporters.",
            "",
            5,
            "/cYY0U8DAkCRAWO6rnIcZ2gW17Fz.jpg",
            10.0,
            1
        )
        val nextEpisodeToAir = DetailTvShow.Response.NextEpisodeToAir(
            "2022-01-10",
            1,
            2910283,
            "Nothing Ever Changes Around Here",
            "",
            "",
            6,
            "",
            10.0,
            0
        )
        val networks = listOf(
            DetailTvShow.Response.Network(
                19,
                "/1DSpHrWyOORkL9N2QHX7Adt31mQ.png",
                "FOX",
                "US"
            )
        )
        val originCountry = listOf("US")
        val productionCompany = listOf(
            DetailTvShow.Response.ProductionCompany(
                2,
                "/wdrCwmRnLFJhEoH8GSfymY85KHT.png",
                "Walt Disney Pictures",
                "US"
            )
        )
        val productionCountry =
            listOf(DetailTvShow.Response.ProductionCountry("US", "United States of America"))
        val seasons = listOf(
            DetailTvShow.Response.Season(
                "2015-07-10",
                4,
                70781,
                "Specials",
                "",
                "/bQ5FupU7DFTbx9pSgPsEZQwyZKj.jpg",
                0
            )
        )
        val spokenLanguage =
            listOf(DetailTvShow.Response.SpokenLanguage("English", "en", "English"))

        val tvshows = ArrayList<DetailTvShow.Response>()

        tvshows.add(
            DetailTvShow.Response(
                "/h48Dpb7ljv8WQvVdyFWVLz64h4G.jpg",
                createdBy,
                episodeRunTime,
                "2016-01-25",
                genres,
                "https://www.netflix.com/title/80057918",
                63174,
                true,
                languages,
                "2021-05-28",
                lastEpisodeToAir,
                "Lucifer",
                networks,
                nextEpisodeToAir,
                93,
                6,
                originCountry,
                "en",
                "Lucifer",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                1456.689,
                "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                productionCompany,
                productionCountry,
                seasons,
                spokenLanguage,
                "Returning Series",
                "It's good to be bad.",
                "Scripted",
                8.5,
                9167
            )
        )

        return tvshows
    }
}