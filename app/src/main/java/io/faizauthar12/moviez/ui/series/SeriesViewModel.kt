package io.faizauthar12.moviez.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import io.faizauthar12.moviez.data.entities.ShowEntity
import io.faizauthar12.moviez.data.source.MovieZRepository
import io.faizauthar12.moviez.util.NetworkState

class SeriesViewModel(private val movieZRepository: MovieZRepository) : ViewModel() {
    fun getSeries(): LiveData<PagedList<ShowEntity>> = movieZRepository.getAllSeries()

    fun getSeriesNetworkState(): LiveData<NetworkState> = movieZRepository.getSeriesNetworkState()

    fun seriesListIsEmpty(): Boolean = getSeries().value?.isEmpty() ?: true
}