package io.faizauthar12.moviez.data.source.remote

import io.faizauthar12.moviez.data.source.remote.response.MovieResponse
import io.faizauthar12.moviez.data.source.remote.response.SerieResponse
import io.faizauthar12.moviez.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper){

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(helper)
                }
    }

    fun getAllMovies(): List<MovieResponse> = jsonHelper.loadMovies()

    fun getAllSeries(): List<SerieResponse> = jsonHelper.loadSeries()
}