package io.faizauthar12.moviez.ui.series

import androidx.lifecycle.ViewModel
import io.faizauthar12.moviez.data.MovieZRepository
import io.faizauthar12.moviez.data.source.local.entity.ShowEntity
import io.faizauthar12.moviez.utils.DataDummy

class SeriesViewModel(private val movieZRepository: MovieZRepository) : ViewModel() {
    fun getSeries(): List<ShowEntity> = movieZRepository.getAllSeries()
}