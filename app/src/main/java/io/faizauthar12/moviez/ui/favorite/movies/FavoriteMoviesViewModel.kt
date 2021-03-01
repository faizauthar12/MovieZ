package io.faizauthar12.moviez.ui.favorite.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import io.faizauthar12.moviez.data.entities.ShowEntity
import io.faizauthar12.moviez.data.source.MovieZRepository

class FavoriteMoviesViewModel(private val movieZRepository: MovieZRepository) : ViewModel(){
    fun getAllFavoriteMovies(): LiveData<PagedList<ShowEntity>> = movieZRepository.getAllFavoriteMovies()
}