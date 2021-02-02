package io.faizauthar12.moviez.ui.series

import androidx.lifecycle.ViewModel
import io.faizauthar12.moviez.data.ShowEntity
import io.faizauthar12.moviez.utils.DataDummy

class SeriesViewModel : ViewModel() {
    fun getSeries(): List<ShowEntity> = DataDummy.generateDummySeries()
}