package io.faizauthar12.moviez.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.faizauthar12.moviez.data.source.local.entity.ShowEntity
import io.faizauthar12.moviez.data.source.remote.RemoteDataSource
import io.faizauthar12.moviez.data.source.remote.response.DataResponse
import io.faizauthar12.moviez.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        EspressoIdlingResource.increment()
        val movies = MutableLiveData<List<ShowEntity>>()
        remoteDataSource.getAllMovies().enqueue(object : Callback<DataResponse> {
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback ->
                        val movieList = arrayListOf<ShowEntity>()
                        for (result in callback.results) {
                            movieList.add(
                                ShowEntity(
                                    result.overview,
                                    result.title,
                                    result.id,
                                    result.posterPath,
                                    result.releaseDate
                                )
                            )
                        }
                        movies.postValue(movieList)
                        EspressoIdlingResource.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return movies
    }

    override fun getAllSeries(): LiveData<List<ShowEntity>> {
        EspressoIdlingResource.increment()
        val series = MutableLiveData<List<ShowEntity>>()
        remoteDataSource.getAllSeries().enqueue(object : Callback<DataResponse> {
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback ->
                        val serieList = arrayListOf<ShowEntity>()
                        for (result in callback.results) {
                            serieList.add(
                                ShowEntity(
                                    result.overview,
                                    result.name,
                                    result.id,
                                    result.posterPath,
                                    result.firstAirDate
                                )
                            )
                        }
                        series.postValue(serieList)
                        EspressoIdlingResource.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return series
    }
}