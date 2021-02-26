package io.faizauthar12.moviez.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "ShowEntity")
data class ShowEntity(
        @ColumnInfo(name = "overview")
        val overview: String?,

        @ColumnInfo(name = "title")
        val Title: String?,

        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        val id: Int?,

        @ColumnInfo(name = "posterPath")
        val posterPath: String?,

        @ColumnInfo(name = "release")
        val release: String?,

        @ColumnInfo(name = "isFavorite")
        val isFavorite: Boolean = false,

        @ColumnInfo(name = "category")
        val category: Int? // 1 = Movies, 2 = Series
) : Parcelable