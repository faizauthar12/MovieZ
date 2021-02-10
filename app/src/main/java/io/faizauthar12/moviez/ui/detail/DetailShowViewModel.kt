package io.faizauthar12.moviez.ui.detail

import androidx.lifecycle.ViewModel
import io.faizauthar12.moviez.data.MovieZRepository
import io.faizauthar12.moviez.data.source.local.entity.ShowEntity
import io.faizauthar12.moviez.utils.DataDummy

class DetailShowViewModel(private val movieZRepository: MovieZRepository) : ViewModel() {
    private lateinit var showsId: String

    fun setSelectedShow(showsId: String) {
        this.showsId = showsId
    }

    // Movies
    fun getMovie(): ShowEntity {
        lateinit var show: ShowEntity
        val showEntities = movieZRepository.getAllMovies()
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
        val showEntities = movieZRepository.getAllSeries()
        for (showEntity in showEntities) {
            if (showEntity.showsId == showsId) {
                show = showEntity
            }
        }
        return show
    }
}