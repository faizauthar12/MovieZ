package io.faizauthar12.moviez.ui.favorite.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import io.faizauthar12.moviez.data.entities.ShowEntity
import io.faizauthar12.moviez.data.source.MovieZRepository

class FavoriteSeriesViewModel(private val movieZRepository: MovieZRepository) : ViewModel() {
    fun getAllFavoriteSeries(): LiveData<PagedList<ShowEntity>> = movieZRepository.getAllFavoriteSeries()
}