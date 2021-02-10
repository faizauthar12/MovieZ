package io.faizauthar12.moviez.data

import io.faizauthar12.moviez.data.source.local.entity.ShowEntity
import io.faizauthar12.moviez.data.source.remote.RemoteDataSource

class MovieZRepository private constructor(private val remoteDataSource: RemoteDataSource): MovieZDataSource {

    companion object {
        @Volatile
        private var instance: MovieZRepository? = null
        fun getInstance(remoteData: RemoteDataSource): MovieZRepository =
                instance ?: synchronized(this) {
                    instance ?: MovieZRepository(remoteData)
                }
    }

    override fun getAllMovies(): ArrayList<ShowEntity> {
        val movieResponse = remoteDataSource.getAllMovies()
        val movieList = ArrayList<ShowEntity>()
        for (response in movieResponse) {
            val movie = ShowEntity(
                    response.showsId,
                    response.title,
                    response.description,
                    response.releaseYear,
                    response.imagePath
            )
            movieList.add(movie)
        }
        return movieList
    }

    override fun getAllSeries(): ArrayList<ShowEntity> {
        val serieResponse = remoteDataSource.getAllSeries()
        val serieList = ArrayList<ShowEntity>()
        for (response in serieResponse) {
            val serie = ShowEntity(
                    response.showsId,
                    response.title,
                    response.description,
                    response.releaseYear,
                    response.imagePath
            )
            serieList.add(serie)
        }
        return serieList
    }
}