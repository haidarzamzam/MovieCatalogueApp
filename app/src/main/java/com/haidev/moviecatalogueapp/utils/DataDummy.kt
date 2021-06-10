package com.haidev.moviecatalogueapp.utils

import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.data.model.ListTvShow

object DataDummy {
    fun generateDummyListMovie(): ListMovie.Response {

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

        return ListMovie.Response(
            1,
            movies,
            500,
            10000
        )
    }

    fun generateDummyListTvShow(): ListTvShow.Response {

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
        return ListTvShow.Response(
            1,
            tvshows,
            500,
            10000
        )
    }
}