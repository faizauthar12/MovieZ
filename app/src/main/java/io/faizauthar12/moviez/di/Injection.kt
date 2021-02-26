package io.faizauthar12.moviez.di

import android.content.Context
import io.faizauthar12.moviez.data.MovieZRepository
import io.faizauthar12.moviez.data.source.local.room.MovieZDatabase
import io.faizauthar12.moviez.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): MovieZRepository {

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = MovieZDatabase.getDatabase(context)
        return MovieZRepository.getInstance(remoteDataSource, localDataSource.movieZDao)
    }
}