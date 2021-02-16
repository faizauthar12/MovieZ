package io.faizauthar12.moviez.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShowEntity(
        var showsId: String,
        var title: String,
        var description: String,
        var releaseYear: String,
        var imagePath: String
) : Parcelable