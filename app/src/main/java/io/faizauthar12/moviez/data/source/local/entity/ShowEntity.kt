package io.faizauthar12.moviez.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShowEntity(
        val overview: String?,
        val Title: String?,
        val id: Int?,
        val posterPath: String?,
        val release: String?
) : Parcelable