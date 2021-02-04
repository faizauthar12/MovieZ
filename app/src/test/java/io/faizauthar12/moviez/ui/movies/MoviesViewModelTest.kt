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
        assertEquals(10,showsEntities.size)
    }
}