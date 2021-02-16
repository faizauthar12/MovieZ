package io.faizauthar12.moviez.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.faizauthar12.moviez.data.source.local.entity.ShowEntity
import io.faizauthar12.moviez.data.source.remote.RemoteDataSource
import io.faizauthar12.moviez.data.source.remote.response.MovieResponse
import io.faizauthar12.moviez.data.source.remote.response.SerieResponse

class MovieZRepository private constructor(private val remoteDataSource: RemoteDataSource): MovieZDataSource {

    companion object {
        @Volatile
        private var instance: MovieZRepository? = null
        fun getInstance(remoteData: RemoteDataSource): MovieZRepository =
                instance ?: synchronized(this) {
                    instance ?: MovieZRepository(remoteData)
                }
    }

    override fun getAllMovies(): LiveData<List<ShowEntity>> {
        val movieResults = MutableLiveData<List<ShowEntity>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<ShowEntity>()
                for (response in movieResponses) {
                    val movie = ShowEntity(
                        response.showsId,
                        response.title,
                        response.description,
                        response.releaseYear,
                        response.imagePath
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })
        return movieResults
    }

    override fun getAllSeries(): LiveData<List<ShowEntity>> {
        val serieResults = MutableLiveData<List<ShowEntity>>()
        remoteDataSource.getAllSeries(object : RemoteDataSource.LoadSeriesCallback {
            override fun onAllSeriesReceived(serieResponses: List<SerieResponse>) {
                val serieList = ArrayList<ShowEntity>()
                for (response in serieResponses) {
                    val serie = ShowEntity(
                        response.showsId,
                        response.title,
                        response.description,
                        response.releaseYear,
                        response.imagePath
                    )
                    serieList.add(serie)
                }
                serieResults.postValue(serieList)
            }
        })
        return serieResults
    }
}