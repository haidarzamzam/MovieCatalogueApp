package com.haidev.moviecatalogueapp.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.haidev.moviecatalogueapp.R
import com.haidev.moviecatalogueapp.utils.EspressoIdlingResource
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {
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
    fun checkTabLayout() {
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withText(R.string.text_tv_show),
                ViewMatchers.isDisplayed()
            )
        ).perform(ViewActions.click())
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withText(R.string.text_movie),
                ViewMatchers.isDisplayed()
            )
        ).perform(ViewActions.click())
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withText(R.string.text_tv_show),
                ViewMatchers.isDisplayed()
            )
        ).perform(ViewActions.click())
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withText(R.string.text_movie),
                ViewMatchers.isDisplayed()
            )
        ).perform(ViewActions.click())
    }

    @Test
    fun clickFavorite() {
        Espresso.onView(ViewMatchers.withId(R.id.fab_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText(R.string.text_tv_show)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText(R.string.text_movie)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.closeFavorite)).perform(ViewActions.click())
    }
}