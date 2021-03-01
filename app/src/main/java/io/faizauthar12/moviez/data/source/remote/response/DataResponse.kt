package io.faizauthar12.moviez.data.source.remote.response

import com.google.gson.annotations.SerializedName

class MovieResponse(

    @field:SerializedName("total_pages")
    var totalPage: Int,
    @field:SerializedName("results")
    var results: List<ResultsItem>
)

class ResultsItem(
    @field:SerializedName("poster_path")
    var posterPath: String?,

    @field:SerializedName("id")
    var id: String?,

    @field:SerializedName("title")
    var title: String?,

    @field:SerializedName("name")
    var name: String?,

    @field:SerializedName("overview")
    var overview: String?,

    @field:SerializedName("release_date")
    var releaseDate: String?,

    @field:SerializedName("first_air_date")
    var firstAirDate: String?
)