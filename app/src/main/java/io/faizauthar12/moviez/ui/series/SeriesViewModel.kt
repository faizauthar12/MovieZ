package io.faizauthar12.moviez.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.faizauthar12.moviez.data.MovieZRepository
import io.faizauthar12.moviez.data.source.local.entity.ShowEntity

class SeriesViewModel(private val movieZRepository: MovieZRepository) : ViewModel() {
    fun getSeries(): LiveData<List<ShowEntity>> = movieZRepository.getAllSeries()
}