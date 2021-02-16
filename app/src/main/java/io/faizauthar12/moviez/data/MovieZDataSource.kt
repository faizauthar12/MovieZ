package io.faizauthar12.moviez.data

import androidx.lifecycle.LiveData
import io.faizauthar12.moviez.data.source.local.entity.ShowEntity

interface MovieZDataSource {

    fun getAllMovies(): LiveData<List<ShowEntity>>

    fun getAllSeries(): LiveData<List<ShowEntity>>
}