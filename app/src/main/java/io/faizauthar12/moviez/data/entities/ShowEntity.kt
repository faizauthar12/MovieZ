package io.faizauthar12.moviez.data.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "moviez_table")
class ShowEntity(
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @PrimaryKey
    @NonNull
    var id: String,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @ColumnInfo(name = "isFavorite")
    val favoriteCategory: Int? // 1 = movie, 2 = series
) : Parcelable