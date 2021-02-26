package io.faizauthar12.moviez.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.faizauthar12.moviez.data.source.local.entity.ShowEntity

@Dao
interface MovieZDao {

    @Query("SELECT * FROM ShowEntity WHERE category = 1 AND isFavorite = 1")
    fun getFavoriteMovies(): LiveData<List<ShowEntity>>

    @Query("SELECT * FROM ShowEntity WHERE category = 2 AND isFavorite = 1")
    fun getFavoriteSeries(): LiveData<List<ShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: List<ShowEntity>)
}