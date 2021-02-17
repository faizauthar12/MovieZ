package io.faizauthar12.moviez.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DataResponse(

    @field:SerializedName("results")
    var results: List<ResultsItem>,
)

data class ResultsItem(

    @field:SerializedName("overview")
    var overview: String?,

    @field:SerializedName("original_title")
    var originalTitle: String?,

    @field:SerializedName("original_name")
    var originalName: String?,

    @field:SerializedName("id")
    var id: Int?,

    @field:SerializedName("poster_path")
    var posterPath: String?,

    @field:SerializedName("first_air_date")
    var firstAirDate: String?,

    @field:SerializedName("release_date")
    var releaseDate: String?
)