package io.faizauthar12.moviez.ui.detail

import io.faizauthar12.moviez.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailShowViewModelTest {

    private lateinit var viewModel: DetailShowViewModel
    private val dummyMovies = DataDummy.generateDummyMovies()[0]
    private val dummySeries = DataDummy.generateDummySeries()[0]
    private val MoviesId = dummyMovies.showsId
    private val SeriesId = dummySeries.showsId

    @Before
    fun moviesSetup(){
        viewModel = DetailShowViewModel()
        viewModel.setSelectedShow(MoviesId)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedShow(dummyMovies.showsId)
        val showEntity = viewModel.getMovie()
        assertNotNull(showEntity)
        assertEquals(dummyMovies.showsId,showEntity.showsId)
        assertEquals(dummyMovies.title,showEntity.title)
        assertEquals(dummyMovies.imagePath,showEntity.imagePath)
        assertEquals(dummyMovies.description,showEntity.description)
        assertEquals(dummyMovies.releaseYear,showEntity.releaseYear)
    }

    @Before
    fun seriesSetup(){
        viewModel = DetailShowViewModel()
        viewModel.setSelectedShow(SeriesId)
    }
    @Test
    fun getSeries() {
        viewModel.setSelectedShow(dummySeries.showsId)
        val showEntity = viewModel.getSeries()
        assertNotNull(showEntity)
        assertEquals(dummySeries.showsId,showEntity.showsId)
        assertEquals(dummySeries.title,showEntity.title)
        assertEquals(dummySeries.imagePath,showEntity.imagePath)
        assertEquals(dummySeries.description,showEntity.description)
        assertEquals(dummySeries.releaseYear,showEntity.releaseYear)
    }
}

/*
    Hasil pengujian menyatakan tidak ada data yang kosong(tidak Null)
 */