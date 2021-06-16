package com.haidev.moviecatalogueapp.ui.tvshow

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.ui.home.HomeActivity
import com.haidev.moviecatalogueapp.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class TvShowFragmentTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadListTvShow() {
        Espresso.onView(ViewMatchers.withText(R.string.text_tv_show)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
    }

    @Test
    fun clickDetailTvShow() {
        Espresso.onView(ViewMatchers.withText(R.string.text_tv_show)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
    }

    @Test
    fun clickBackDetailTvShow() {
        Espresso.onView(ViewMatchers.withText(R.string.text_tv_show)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        Espresso.onView(ViewMatchers.withId(R.id.btn_back)).perform(ViewActions.click())
    }

    @Test
    fun clickShareDetailTvShow() {
        Espresso.onView(ViewMatchers.withText(R.string.text_tv_show)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        Espresso.onView(ViewMatchers.withId(R.id.btn_share)).perform(ViewActions.click())
    }

    @Test
    fun clickFavoriteDetailTvShow() {
        Espresso.onView(ViewMatchers.withText(R.string.text_tv_show)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        Espresso.onView(ViewMatchers.withId(R.id.btn_share)).perform(ViewActions.click())
    }
}