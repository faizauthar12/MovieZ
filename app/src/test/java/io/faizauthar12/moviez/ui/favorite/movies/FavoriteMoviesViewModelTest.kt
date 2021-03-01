package io.faizauthar12.moviez.ui.favorite.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import io.faizauthar12.moviez.data.entities.ShowEntity
import io.faizauthar12.moviez.data.source.MovieZRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class FavoriteMoviesViewModelTest {
    private lateinit var viewModel: FavoriteMoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieZRepository

    @Mock
    private lateinit var observer: Observer<PagedList<ShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<ShowEntity>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = FavoriteMoviesViewModel(repository)
    }

    @Test
    fun getAllFavoriteMovies() {
        val dummyData = pagedList
        Mockito.`when`(dummyData.size).thenReturn(5)
        val movies = MutableLiveData<PagedList<ShowEntity>>()
        movies.value = dummyData

        Mockito.`when`(repository.getAllFavoriteMovies()).thenReturn(movies)
        val movieEntities = viewModel.getAllFavoriteMovies().value
        Mockito.verify(repository).getAllFavoriteMovies()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(5, movieEntities?.size)

        viewModel.getAllFavoriteMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyData)
    }
}