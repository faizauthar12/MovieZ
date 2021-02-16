package io.faizauthar12.moviez.data.source.remote

import android.os.Handler
import android.os.Looper
import io.faizauthar12.moviez.data.source.remote.response.MovieResponse
import io.faizauthar12.moviez.data.source.remote.response.SerieResponse
import io.faizauthar12.moviez.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper){

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(helper)
                }
    }

    fun getAllMovies(callback: LoadMoviesCallback) {
        handler.postDelayed({ callback.onAllMoviesReceived(jsonHelper.loadMovies())}, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllSeries(callback: LoadSeriesCallback) {
        handler.postDelayed({ callback.onAllSeriesReceived(jsonHelper.loadSeries())}, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponses: List<MovieResponse>)
    }

    interface LoadSeriesCallback {
        fun onAllSeriesReceived(serieResponses: List<SerieResponse>)
    }
}