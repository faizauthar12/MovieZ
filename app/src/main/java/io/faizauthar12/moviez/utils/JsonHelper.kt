package io.faizauthar12.moviez.utils

import android.content.Context
import io.faizauthar12.moviez.data.source.remote.response.MovieResponse
import io.faizauthar12.moviez.data.source.remote.response.SerieResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<MovieResponse> {
        val list = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponses.json").toString())
            val listArray = responseObject.getJSONArray("movies")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val showsId = movie.getString("showsId")
                val title = movie.getString("title")
                val description = movie.getString("description")
                val releaseYear = movie.getString("releaseYear")
                val imagePath = movie.getString("imagePath")

                val movieResponse = MovieResponse(showsId, title, description, releaseYear, imagePath)
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadSeries(): List<SerieResponse> {
        val list = ArrayList<SerieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("SerieResponses.json").toString())
            val listArray = responseObject.getJSONArray("series")
            for (i in 0 until listArray.length()) {
                val serie = listArray.getJSONObject(i)

                val showsId = serie.getString("showsId")
                val title = serie.getString("title")
                val description = serie.getString("description")
                val releaseYear = serie.getString("releaseYear")
                val imagePath = serie.getString("imagePath")

                val serieResponse = SerieResponse(showsId, title, description, releaseYear, imagePath)
                list.add(serieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }
}