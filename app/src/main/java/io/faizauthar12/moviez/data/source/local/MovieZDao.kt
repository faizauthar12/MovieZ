package io.faizauthar12.moviez.data.source.local

import androidx.paging.DataSource
import androidx.room.*
import io.faizauthar12.moviez.data.entities.ShowEntity

@Dao
interface MovieZDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(show: ShowEntity)

    @Delete
    fun delete(show: ShowEntity)

    @Query("SELECT * FROM moviez_table WHERE isFavorite = 1")
    fun getAllFavoriteMovies(): DataSource.Factory<Int, ShowEntity>

    @Query("SELECT * FROM moviez_table WHERE isFavorite = 2")
    fun getAllFavoriteSeries(): DataSource.Factory<Int, ShowEntity>
}