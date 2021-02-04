package io.faizauthar12.moviez.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.utils.DataDummy
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummySeries = DataDummy.generateDummySeries()

    @Before
    fun setup(){
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun loadMovies(){
        Espresso.onView(withText("Movies")).perform(click())
        Espresso.onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(withText("Movies")).perform(click())
        Espresso.onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        Espresso.onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_title)).check(matches(withText(dummyMovies[0].title)))
        Espresso.onView(withId(R.id.tv_release_year)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_release_year)).check(matches(withText(dummyMovies[0].releaseYear)))
        Espresso.onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_description)).check(matches(withText(dummyMovies[0].description)))
    }

    @Test
    fun loadSeries(){
        Espresso.onView(withText("Tv/Shows")).perform(click())
        Espresso.onView(withId(R.id.rv_series)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_series)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummySeries.size))
    }

    @Test
    fun loadDetailSerie(){
        Espresso.onView(withText("Tv/Shows")).perform(click())
        Espresso.onView(withId(R.id.rv_series)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        Espresso.onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_title)).check(matches(withText(dummySeries[0].title)))
        Espresso.onView(withId(R.id.tv_release_year)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_release_year)).check(matches(withText(dummySeries[0].releaseYear)))
        Espresso.onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_description)).check(matches(withText(dummySeries[0].description)))
    }
}

/*
    Semua test berjalan dengan lancar.
    Sebelum menguji tabs Tv/Shows saya diharuskan untuk menekan tombol tabs tersebut terlebih dahulu, jika tidak test akan error.
    Oleh karena itu saya implementasikan click pada tiap function, untuk mencegah kegagalan saat pengujian.
 */