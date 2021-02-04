package io.faizauthar12.moviez.ui.movies

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setUp(){
        viewModel = MoviesViewModel()
    }

    @Test
    fun getMovies() {
        val showsEntities = viewModel.getMovies()
        assertNotNull(showsEntities)
        assertEquals(5,showsEntities.size)
    }
}

/*
    Ketika dilakukan instrumen testing assertEquals berjumlah 5. Maka hasil test akan error.
    Karena data sebenarnya ialah 10.
    Kode error :
    java.lang.AssertionError:
    Expected :5
    Actual   :10
 */