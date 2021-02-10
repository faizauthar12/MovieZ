package io.faizauthar12.moviez.di

import android.content.Context
import io.faizauthar12.moviez.data.MovieZRepository
import io.faizauthar12.moviez.data.source.remote.RemoteDataSource
import io.faizauthar12.moviez.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovieZRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return MovieZRepository.getInstance(remoteDataSource)
    }
}