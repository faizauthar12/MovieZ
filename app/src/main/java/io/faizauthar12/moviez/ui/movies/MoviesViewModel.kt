package io.faizauthar12.moviez.ui.movies

import androidx.lifecycle.ViewModel
import io.faizauthar12.moviez.data.ShowEntity
import io.faizauthar12.moviez.utils.DataDummy

class MoviesViewModel : ViewModel() {

    fun getMovies(): List<ShowEntity> = DataDummy.generateDummyMovies()
}