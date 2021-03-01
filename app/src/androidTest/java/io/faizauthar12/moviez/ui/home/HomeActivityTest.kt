package io.faizauthar12.moviez.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.Matchers.allOf
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovies(){
        Espresso.onView(withId(R.id.moviesFragment)).perform(click())
        Espresso.onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(withId(R.id.moviesFragment)).perform(click())
        Espresso.onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        Espresso.onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3,click()))
        Espresso.onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_release_year)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.fab)).perform(click())
        Espresso.pressBack()
    }

    @Test
    fun loadSeries(){
        Espresso.onView(withId(R.id.seriesFragment)).perform(click())
        Espresso.onView(withId(R.id.rv_series)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailSeries(){
        Espresso.onView(withId(R.id.seriesFragment)).perform(click())
        Espresso.onView(withId(R.id.rv_series)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        Espresso.onView(withId(R.id.rv_series)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3,click()))
        Espresso.onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_release_year)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.fab)).perform(click())
        Espresso.pressBack()
    }

    @Test
    fun loadFavoriteMovies(){
        Espresso.onView(withId(R.id.favoriteFragment)).perform(click())
        Espresso.onView(allOf(withText(R.string.movies), isDescendantOfA(withId(R.id.tabsFavorite))))
                .perform(click())
                .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_favorite_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailFavoriteMovies(){
        Espresso.onView(withId(R.id.favoriteFragment)).perform(click())
        Espresso.onView(allOf(withText(R.string.movies), isDescendantOfA(withId(R.id.tabsFavorite))))
                .perform(click())
                .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_favorite_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        Espresso.onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_release_year)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.fab)).perform(click())
        Espresso.pressBack()
    }

    @Test
    fun loadFavoriteSeries(){
        Espresso.onView(withId(R.id.favoriteFragment)).perform(click())
        Espresso.onView(allOf(withText(R.string.tv_shows), isDescendantOfA(withId(R.id.tabsFavorite))))
                .perform(click())
                .check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailFavoriteSeries(){
        Espresso.onView(withId(R.id.favoriteFragment)).perform(click())
        Espresso.onView(allOf(withText(R.string.tv_shows), isDescendantOfA(withId(R.id.tabsFavorite))))
                .perform(click())
                .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_favorite_serie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        Espresso.onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_release_year)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.fab)).perform(click())
        Espresso.pressBack()
    }
}