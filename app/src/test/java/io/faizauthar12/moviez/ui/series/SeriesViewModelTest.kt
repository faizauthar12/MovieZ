package io.faizauthar12.moviez.ui.series

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.faizauthar12.moviez.data.MovieZRepository
import io.faizauthar12.moviez.data.source.local.entity.ShowEntity
import io.faizauthar12.moviez.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SeriesViewModelTest {

    private lateinit var viewModel: SeriesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieZRepository: MovieZRepository

    @Mock
    private lateinit var observer: Observer<List<ShowEntity>>

    @Before
    fun setUp(){
        viewModel = SeriesViewModel(movieZRepository)
    }

    @Test
    fun getSeries() {
        val dummySeries = DataDummy.generateDummySeries()
        val series = MutableLiveData<List<ShowEntity>>()
        series.value = dummySeries

        Mockito.`when`(movieZRepository.getAllSeries()).thenReturn(series)
        val showsEntities = viewModel.getSeries().value
        Mockito.verify(movieZRepository).getAllSeries()
        assertNotNull(showsEntities)
        assertEquals(10,showsEntities?.size)

        viewModel.getSeries().observeForever(observer)
        Mockito.verify(observer).onChanged(dummySeries)
    }
}