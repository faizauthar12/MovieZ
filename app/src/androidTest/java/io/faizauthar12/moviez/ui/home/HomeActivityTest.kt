package io.faizauthar12.moviez.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.data.MovieZDataSource
import io.faizauthar12.moviez.data.MovieZRepository
import io.faizauthar12.moviez.data.source.local.entity.ShowEntity
import io.faizauthar12.moviez.data.source.remote.RemoteDataSource
import io.faizauthar12.moviez.ui.movies.MoviesAdapter
import io.faizauthar12.moviez.utils.DataDummy
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
        Espresso.onView(withText("Movies")).perform(click())
        Espresso.onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(withText("Movies")).perform(click())
        Espresso.onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3,click()))
        Espresso.onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_release_year)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
    }

    @Test
    fun loadSeries(){
        Espresso.onView(withText("Tv/Shows")).perform(click())
        Espresso.onView(withId(R.id.rv_series)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailSerie(){
        Espresso.onView(withText("Tv/Shows")).perform(click())
        Espresso.onView(withId(R.id.rv_series)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5,click()))
        Espresso.onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_release_year)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
    }
}