package io.faizauthar12.moviez.di

import android.content.Context
import io.faizauthar12.moviez.data.source.MovieZRepository
import io.faizauthar12.moviez.data.source.local.MovieZDatabase
import io.faizauthar12.moviez.data.source.remote.response.ApiService

object Injection {
    fun provideRepository(context: Context): MovieZRepository {

        val remoteDataSource = ApiService.getInstance()
        val localDataSource = MovieZDatabase.getDatabase(context)
        return MovieZRepository.getInstance(remoteDataSource, localDataSource.movieZDao)
    }
}