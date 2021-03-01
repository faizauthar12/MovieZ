package io.faizauthar12.moviez.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import io.faizauthar12.moviez.data.entities.ShowEntity
import io.faizauthar12.moviez.data.source.MovieZRepository
import io.faizauthar12.moviez.util.NetworkState

class MoviesViewModel(private val movieZRepository: MovieZRepository) : ViewModel() {

    fun getMovies(): LiveData<PagedList<ShowEntity>> = movieZRepository.getAllMovies()

    fun getMoviesNetworkState(): LiveData<NetworkState> = movieZRepository.getMoviesNetworkState()

    fun moviesListIsEmpty(): Boolean = getMovies().value?.isEmpty() ?: true
}