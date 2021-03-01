package io.faizauthar12.moviez.ui.series

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
class SeriesViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieZRepository: MovieZRepository
    private lateinit var viewModel: SeriesViewModel

    @Mock
    private lateinit var observer: Observer<PagedList<ShowEntity>>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = SeriesViewModel(movieZRepository)
    }

    @Test
    fun getSeries() {
        val mutableDummyData = MutableLiveData<PagedList<ShowEntity>>()
        val dummyContent = PagedListUtil.mockPagedList(DataDummy.generateDummySeries())
        mutableDummyData.postValue(dummyContent)
        val dummyData: LiveData<PagedList<ShowEntity>> = mutableDummyData


        `when`(movieZRepository.getAllSeries()).thenReturn(dummyData)
        val courseEntities = viewModel.getSeries().value
        verify(movieZRepository).getAllSeries()
        Assert.assertNotNull(courseEntities)
        Assert.assertEquals(1, courseEntities?.size)

        viewModel.getSeries().observeForever(observer)
        verify(observer).onChanged(dummyContent)
    }
}