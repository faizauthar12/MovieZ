package io.faizauthar12.moviez.data.source.remote

import io.faizauthar12.moviez.data.source.remote.request.ApiRequest
import io.faizauthar12.moviez.data.source.remote.request.ApiRequest.Companion.API_KEY
import io.faizauthar12.moviez.data.source.remote.request.ApiRequest.Companion.BASE_URL
import io.faizauthar12.moviez.data.source.remote.request.ApiRequest.Companion.LANGUAGE_PREF
import io.faizauthar12.moviez.data.source.remote.request.ApiRequest.Companion.PAGE
import io.faizauthar12.moviez.data.source.remote.response.DataResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }

        fun getApiService(): ApiRequest {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiRequest::class.java)
        }
    }

    fun getAllMovies(): Call<DataResponse> =
        getApiService().getAllMovies(API_KEY, LANGUAGE_PREF, PAGE)

    fun getAllSeries(): Call<DataResponse> =
        getApiService().getAllSeries(API_KEY, LANGUAGE_PREF, PAGE)
}