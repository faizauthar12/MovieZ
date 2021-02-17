package io.faizauthar12.moviez.di

import io.faizauthar12.moviez.data.MovieZRepository
import io.faizauthar12.moviez.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): MovieZRepository {

        val remoteDataSource = RemoteDataSource.getInstance()

        return MovieZRepository.getInstance(remoteDataSource)
    }
}