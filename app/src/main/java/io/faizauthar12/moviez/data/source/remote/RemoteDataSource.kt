package io.faizauthar12.moviez.data.source.remote

import android.os.Handler
import android.os.Looper
import io.faizauthar12.moviez.utils.EspressoIdlingResource

class RemoteDataSource {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource()
                }
    }

    fun getAllMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllMoviesReceived(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllSeries(callback: LoadSeriesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllSeriesReceived(jsonHelper.loadSeries())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponses: List<MovieResponse>)
    }

    interface LoadSeriesCallback {
        fun onAllSeriesReceived(serieResponses: List<SerieResponse>)
    }
}