package io.faizauthar12.moviez.data.source.remote.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.faizauthar12.moviez.data.entities.ShowEntity
import io.faizauthar12.moviez.data.source.remote.response.ApiService
import io.faizauthar12.moviez.data.source.remote.response.MovieResponse
import io.faizauthar12.moviez.util.NetworkState
import io.faizauthar12.moviez.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeriesDataSource(private val apiService: ApiService) :
    PageKeyedDataSource<Int, ShowEntity>() {

    private var page = 1
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ShowEntity>) {
        EspressoIdlingResource.increment()

        networkState.postValue(NetworkState.running())

        apiService.getAllSeries(page).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { networkCallback ->
                        val seriesList = arrayListOf<ShowEntity>()
                        for (result in networkCallback.results) {
                            seriesList.add(
                                ShowEntity(
                                    result.posterPath,
                                    result.id ?: "",
                                    result.name,
                                    result.overview,
                                    result.firstAirDate,
                                    2
                                )
                            )
                        }
                        callback.onResult(seriesList, null, page + 1)
                        networkState.postValue(NetworkState.success())
                        EspressoIdlingResource.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                networkState.postValue(NetworkState.error())
                t.printStackTrace()
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ShowEntity>) {
        EspressoIdlingResource.increment()

        networkState.postValue(NetworkState.running())

        apiService.getAllSeries(params.key).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { networkCallback ->
                        val seriesList = arrayListOf<ShowEntity>()
                        for (result in networkCallback.results) {
                            seriesList.add(
                                ShowEntity(
                                    result.posterPath,
                                    result.id ?: "",
                                    result.name,
                                    result.overview,
                                    result.firstAirDate,
                                    2
                                )
                            )
                        }
                        if (networkCallback.totalPage >= params.key) {
                            callback.onResult(seriesList, params.key + 1)
                            networkState.postValue(NetworkState.success())
                            EspressoIdlingResource.decrement()
                        } else {
                            networkState.postValue(NetworkState.reachedBottomList())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                networkState.postValue(NetworkState.error())
                t.printStackTrace()
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ShowEntity>) {}
}