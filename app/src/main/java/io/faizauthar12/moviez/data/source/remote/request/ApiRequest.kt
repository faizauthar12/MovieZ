package io.faizauthar12.moviez.data.source.remote.request

import io.faizauthar12.moviez.BuildConfig
import io.faizauthar12.moviez.data.source.remote.response.DataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {

    @GET("3/movie/popular")
    fun getAllMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<DataResponse>

    @GET("3/tv/popular")
    fun getAllSeries(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<DataResponse>

    companion object {
        const val API_KEY = BuildConfig.ApiKey
        const val BASE_IMAGE_URL = BuildConfig.BaseImageUrl
        const val BASE_URL = BuildConfig.BaseUrl
        const val LANGUAGE_PREF = "en-US"
        const val PAGE = 1
    }
}