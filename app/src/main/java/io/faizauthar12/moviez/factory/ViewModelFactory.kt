package io.faizauthar12.moviez.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.faizauthar12.moviez.data.source.MovieZRepository
import io.faizauthar12.moviez.di.Injection
import io.faizauthar12.moviez.ui.detail.DetailShowViewModel
import io.faizauthar12.moviez.ui.favorite.movies.FavoriteMoviesViewModel
import io.faizauthar12.moviez.ui.favorite.series.FavoriteSeriesViewModel
import io.faizauthar12.moviez.ui.movies.MoviesViewModel
import io.faizauthar12.moviez.ui.series.SeriesViewModel

class ViewModelFactory (private val mMovieZRepository: MovieZRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository(context))
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(mMovieZRepository) as T
            }
            modelClass.isAssignableFrom(SeriesViewModel::class.java) -> {
                SeriesViewModel(mMovieZRepository) as T
            }
            modelClass.isAssignableFrom(DetailShowViewModel::class.java) -> {
                DetailShowViewModel(mMovieZRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMoviesViewModel::class.java) -> {
                FavoriteMoviesViewModel(mMovieZRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteSeriesViewModel::class.java) -> {
                FavoriteSeriesViewModel(mMovieZRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}