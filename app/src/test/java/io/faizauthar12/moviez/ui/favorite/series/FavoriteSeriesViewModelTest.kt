package io.faizauthar12.moviez.ui.favorite.series

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
class FavoriteSeriesViewModelTest {
    private lateinit var viewModel: FavoriteSeriesViewModel

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
        viewModel = FavoriteSeriesViewModel(repository)
    }

    @Test
    fun getAllFavoriteSeries() {
        val dummyTvShows = pagedList
        Mockito.`when`(dummyTvShows.size).thenReturn(5)
        val movies = MutableLiveData<PagedList<ShowEntity>>()
        movies.value = dummyTvShows

        Mockito.`when`(repository.getAllFavoriteSeries()).thenReturn(movies)
        val tvShowEntities = viewModel.getAllFavoriteSeries().value
        Mockito.verify(repository).getAllFavoriteSeries()
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(5, tvShowEntities?.size)

        viewModel.getAllFavoriteSeries().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShows)
    }
}