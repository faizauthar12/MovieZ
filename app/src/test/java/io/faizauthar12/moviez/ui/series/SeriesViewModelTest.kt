package io.faizauthar12.moviez.ui.series

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class SeriesViewModelTest {

    private lateinit var viewModel: SeriesViewModel

    @Before
    fun setUp(){
        viewModel = SeriesViewModel()
    }

    @Test
    fun getSeries() {
        val showsEntities = viewModel.getSeries()
        assertNotNull(showsEntities)
        assertEquals(10,showsEntities.size)
    }
}