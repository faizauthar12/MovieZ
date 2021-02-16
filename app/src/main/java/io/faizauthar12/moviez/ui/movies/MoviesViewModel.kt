package io.faizauthar12.moviez.ui.movies

import androidx.lifecycle.ViewModel
import io.faizauthar12.moviez.data.MovieZRepository
import io.faizauthar12.moviez.data.source.local.entity.ShowEntity

class MoviesViewModel(private val movieZRepository: MovieZRepository) : ViewModel() {

    fun getMovies(): List<ShowEntity> = movieZRepository.getAllMovies()
}