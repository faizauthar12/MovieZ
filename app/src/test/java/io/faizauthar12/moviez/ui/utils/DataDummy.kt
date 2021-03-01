package io.faizauthar12.moviez.ui.utils

import io.faizauthar12.moviez.data.entities.ShowEntity

object DataDummy {
    fun generateDummyMovies(): List<ShowEntity> {
        val movies: ArrayList<ShowEntity> = ArrayList()
        movies.add(
            ShowEntity(
                "/jlJ8nDhMhCYJuzOw3f52CP1W8MW.jpg",
                "1",
                "Alita: Battle Angel",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "2019",
                1,
            )
        )
        return movies
    }

    fun generateDummySeries(): List<ShowEntity> {
        val series: ArrayList<ShowEntity> = ArrayList()
        series.add(
            ShowEntity(
                "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                "1",
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "2012",
                2
            )
        )
        return series
    }
}

