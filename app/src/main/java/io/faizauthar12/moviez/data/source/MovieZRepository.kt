package io.faizauthar12.moviez.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.faizauthar12.moviez.data.entities.ShowEntity
import io.faizauthar12.moviez.data.source.local.MovieZDao
import io.faizauthar12.moviez.data.source.remote.paging.MoviesDataSource
import io.faizauthar12.moviez.data.source.remote.paging.SeriesDataSource
import io.faizauthar12.moviez.data.source.remote.response.ApiService
import io.faizauthar12.moviez.factory.MoviesDataSourceFactory
import io.faizauthar12.moviez.factory.SeriesDataSourceFactory
import io.faizauthar12.moviez.util.NetworkState

open class MovieZRepository(
    private val remoteDataSource: ApiService,
    private val localDataSource: MovieZDao
) : MovieZDataSource {

    private val moviesDataSourceFactory: MoviesDataSourceFactory =
        MoviesDataSourceFactory(remoteDataSource)
    private val seriesDataSourceFactory: SeriesDataSourceFactory =
        SeriesDataSourceFactory(remoteDataSource)

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(5)
        .setInitialLoadSizeHint(10)
        .build()

    override fun getAllMovies(): LiveData<PagedList<ShowEntity>> {
        return LivePagedListBuilder(moviesDataSourceFactory, config).build()
    }

    override fun getAllSeries(): LiveData<PagedList<ShowEntity>> {
        return LivePagedListBuilder(seriesDataSourceFactory, config).build()
    }

    override fun getAllFavoriteMovies(): LiveData<PagedList<ShowEntity>> {
        return LivePagedListBuilder(localDataSource.getAllFavoriteMovies(), 10).build()
    }

    override fun getAllFavoriteSeries(): LiveData<PagedList<ShowEntity>> {
        return LivePagedListBuilder(localDataSource.getAllFavoriteSeries(), 10).build()
    }

    override suspend fun insertFavorite(show: ShowEntity) {
        localDataSource.insert(show)
    }

    override suspend fun deleteFavorite(show: ShowEntity) {
        localDataSource.delete(show)
    }

    fun getSeriesNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap(
            seriesDataSourceFactory.seriesLiveDataSource, SeriesDataSource::networkState
        )
    }

    fun getMoviesNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap(
            moviesDataSourceFactory.movieLiveDataSource, MoviesDataSource::networkState
        )
    }

    companion object {
        @Volatile
        private var instance: MovieZRepository? = null
        fun getInstance(remoteData: ApiService, localData: MovieZDao): MovieZRepository =
            instance ?: synchronized(this) {
                instance ?: MovieZRepository(remoteData, localData)
            }
    }
}