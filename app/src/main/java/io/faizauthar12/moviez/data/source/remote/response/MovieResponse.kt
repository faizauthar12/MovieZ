package io.faizauthar12.moviez.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
        var showsId: String,
        var title: String,
        var description: String,
        var releaseYear: String,
        var imagePath: String
) : Parcelable