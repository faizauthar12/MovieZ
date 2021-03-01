package io.faizauthar12.moviez.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.faizauthar12.moviez.data.entities.ShowEntity
import io.faizauthar12.moviez.data.source.remote.paging.SeriesDataSource
import io.faizauthar12.moviez.data.source.remote.response.ApiService

class SeriesDataSourceFactory(private val apiService: ApiService) :
    DataSource.Factory<Int, ShowEntity>() {

    val seriesLiveDataSource = MutableLiveData<SeriesDataSource>()

    override fun create(): DataSource<Int, ShowEntity> {
        val seriesDataSource = SeriesDataSource(apiService)
        seriesLiveDataSource.postValue(seriesDataSource)
        return seriesDataSource
    }
}