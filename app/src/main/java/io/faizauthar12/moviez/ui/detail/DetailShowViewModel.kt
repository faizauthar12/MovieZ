package io.faizauthar12.moviez.ui.detail

import androidx.lifecycle.ViewModel
import io.faizauthar12.moviez.data.ShowEntity
import io.faizauthar12.moviez.utils.DataDummy

class DetailShowViewModel : ViewModel() {
    private lateinit var showsId: String

    fun setSelectedShow(showsId: String) {
        this.showsId = showsId
    }

    // Movies
    fun getMovie(): ShowEntity {
        lateinit var show: ShowEntity
        val showEntities = DataDummy.generateDummyMovies()
        for (showEntity in showEntities) {
            if (showEntity.showsId == showsId) {
                show = showEntity
            }
        }
        return show
    }

    // Series
    fun getSeries(): ShowEntity {
        lateinit var show: ShowEntity
        val showEntities = DataDummy.generateDummySeries()
        for (showEntity in showEntities) {
            if (showEntity.showsId == showsId) {
                show = showEntity
            }
        }
        return show
    }
}