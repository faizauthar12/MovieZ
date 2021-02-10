package io.faizauthar12.moviez.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.faizauthar12.moviez.data.MovieZRepository
import io.faizauthar12.moviez.di.Injection
import io.faizauthar12.moviez.ui.detail.DetailShowViewModel
import io.faizauthar12.moviez.ui.movies.MoviesViewModel
import io.faizauthar12.moviez.ui.series.SeriesViewModel

class ViewModelFactory private constructor(private val mMovieZRepository: MovieZRepository) : ViewModelProvider.NewInstanceFactory() {

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
        when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                return MoviesViewModel(mMovieZRepository) as T
            }
            modelClass.isAssignableFrom(SeriesViewModel::class.java) -> {
                return SeriesViewModel(mMovieZRepository) as T
            }
            modelClass.isAssignableFrom(DetailShowViewModel::class.java) -> {
                return DetailShowViewModel(mMovieZRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}