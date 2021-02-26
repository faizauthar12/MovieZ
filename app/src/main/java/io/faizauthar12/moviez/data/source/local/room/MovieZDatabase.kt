package io.faizauthar12.moviez.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.faizauthar12.moviez.data.source.local.entity.ShowEntity

@Database(entities = [ShowEntity::class], version = 1, exportSchema = false)
abstract class MovieZDatabase: RoomDatabase() {

    abstract val movieZDao: MovieZDao

    companion object {
        @Volatile
        private var INSTANCE: MovieZDatabase? = null

        fun getDatabase(context: Context): MovieZDatabase {
            if (INSTANCE == null) {
                synchronized(MovieZDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MovieZDatabase::class.java,"MovieZ_database"
                        ).build()
                    }
                }
            }
            return INSTANCE as MovieZDatabase
        }
    }

}