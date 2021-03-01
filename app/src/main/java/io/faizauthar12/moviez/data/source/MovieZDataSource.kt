package io.faizauthar12.moviez.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import io.faizauthar12.moviez.data.entities.ShowEntity


interface MovieZDataSource {

    fun getAllMovies(): LiveData<PagedList<ShowEntity>>

    fun getAllSeries(): LiveData<PagedList<ShowEntity>>

    fun getAllFavoriteMovies(): LiveData<PagedList<ShowEntity>>

    fun getAllFavoriteSeries(): LiveData<PagedList<ShowEntity>>

    suspend fun insertFavorite(show: ShowEntity)

    suspend fun deleteFavorite(show: ShowEntity)

}