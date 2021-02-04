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
        assertEquals(3,showsEntities.size)
    }
}

/*
    Ketika dilakukan instrumen testing assertEquals berjumlah 3. Maka hasil test akan error.
    Karena data sebenarnya ialah 10.
    Kode error :
    java.lang.AssertionError:
    Expected :3
    Actual   :10
 */