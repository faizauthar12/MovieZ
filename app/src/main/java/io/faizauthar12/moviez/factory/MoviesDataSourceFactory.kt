package io.faizauthar12.moviez.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.faizauthar12.moviez.data.entities.ShowEntity
import io.faizauthar12.moviez.data.source.remote.paging.MoviesDataSource
import io.faizauthar12.moviez.data.source.remote.response.ApiService

class MoviesDataSourceFactory(private val apiService: ApiService) :
    DataSource.Factory<Int, ShowEntity>() {

    val movieLiveDataSource = MutableLiveData<MoviesDataSource>()

    override fun create(): DataSource<Int, ShowEntity> {
        val movieDataSource = MoviesDataSource(apiService)
        movieLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}