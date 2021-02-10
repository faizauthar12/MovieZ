package io.faizauthar12.moviez.data

import io.faizauthar12.moviez.data.source.local.entity.ShowEntity

interface MovieZDataSource {

    fun getAllMovies(): List<ShowEntity>

    fun getAllSeries(): List<ShowEntity>
}