package io.faizauthar12.moviez.ui.movies

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
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieZRepository: MovieZRepository

    @Mock
    private lateinit var observer: Observer<List<ShowEntity>>

    @Before
    fun setUp(){
        viewModel = MoviesViewModel(movieZRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = DataDummy.generateDummyMovies()
        val movies = MutableLiveData<List<ShowEntity>>()
        movies.value = dummyMovies

        `when`(movieZRepository.getAllMovies()).thenReturn(movies)
        val showsEntities = viewModel.getMovies().value
        verify(movieZRepository).getAllMovies()
        assertNotNull(showsEntities)
        assertEquals(10,showsEntities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}