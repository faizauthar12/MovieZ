package io.faizauthar12.moviez.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import io.faizauthar12.moviez.data.entities.ShowEntity
import io.faizauthar12.moviez.data.source.MovieZRepository
import io.faizauthar12.moviez.ui.utils.DataDummy
import io.faizauthar12.moviez.ui.navigation.utils.PagedListUtil
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MoviesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieZRepository: MovieZRepository
    private lateinit var viewModel: MoviesViewModel

    @Mock
    private lateinit var observer: Observer<PagedList<ShowEntity>>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = MoviesViewModel(movieZRepository)
    }

    @Test
    fun getMovies() {
        val mutableDummyData = MutableLiveData<PagedList<ShowEntity>>()
        val dummyContent = PagedListUtil.mockPagedList(DataDummy.generateDummyMovies())
        mutableDummyData.postValue(dummyContent)
        val dummyData: LiveData<PagedList<ShowEntity>> = mutableDummyData


        `when`(movieZRepository.getAllMovies()).thenReturn(dummyData)
        val courseEntities = viewModel.getMovies().value
        verify(movieZRepository).getAllMovies()
        Assert.assertNotNull(courseEntities)
        Assert.assertEquals(1, courseEntities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyContent)
    }
}