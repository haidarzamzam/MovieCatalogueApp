package com.haidev.moviecatalogueapp.ui.movie

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.data.model.Resource
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.utils.TestCoroutineRule
import com.haidev.moviecatalogueapp.utils.DataDummy
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    @get:Rule
    val textInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var app: Application

    @Mock
    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var navigator: MovieNavigator

    @Mock
    private lateinit var repo: ApiRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<ListMovie.Response.Result>>>

    private val dummyMovies = DataDummy.generateDummyListMovie()

    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel =
            MovieViewModel(
                repo,
                app
            )
        viewModel.navigator = navigator
    }

    @Test
    fun `getMovies should be success`() {
        testCoroutineRule.runBlockingTest {
            val movies = PagedTestDataSources.snapshot(dummyMovies)
            val expected = MutableLiveData<Resource<PagedList<ListMovie.Response.Result>>>()
            expected.value = Resource.success(movies)

            Mockito.`when`(repo.getListMovie()).thenReturn(expected)

            viewModel.getAllListMovie().observeForever(observer)
            Mockito.verify(observer).onChanged(expected.value)

            val expectedValue = expected.value
            val actualValue = viewModel.getAllListMovie().value
            assertEquals(expectedValue, actualValue)
            assertEquals(expectedValue?.data, actualValue?.data)
            assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
        }
    }

    @Test
    fun `getMovies should be success but data is empty`() {
        testCoroutineRule.runBlockingTest {
            val courses = PagedTestDataSources.snapshot()
            val expected = MutableLiveData<Resource<PagedList<ListMovie.Response.Result>>>()
            expected.value = Resource.success(courses)

            Mockito.`when`(repo.getListMovie()).thenReturn(expected)

            viewModel.getAllListMovie().observeForever(observer)
            Mockito.verify(observer).onChanged(expected.value)

            val actualValueDataSize = viewModel.getAllListMovie().value?.data?.size
            Assert.assertTrue(
                "size of data should be 0, actual is $actualValueDataSize",
                actualValueDataSize == 0
            )
        }
    }

    @Test
    fun `getMovies should be error`() {
        testCoroutineRule.runBlockingTest {
            val expectedMessage = "Something happen dude!"
            val expected = MutableLiveData<Resource<PagedList<ListMovie.Response.Result>>>()
            expected.value = Resource.error(expectedMessage, null)

            Mockito.`when`(repo.getListMovie()).thenReturn(expected)

            viewModel.getAllListMovie().observeForever(observer)
            Mockito.verify(observer).onChanged(expected.value)

            val actualMessage = viewModel.getAllListMovie().value?.message
            Assert.assertEquals(expectedMessage, actualMessage)
        }
    }

    class PagedTestDataSources private constructor(private val items: List<ListMovie.Response.Result>) :
        PositionalDataSource<ListMovie.Response.Result>() {
        companion object {
            fun snapshot(items: List<ListMovie.Response.Result> = listOf()): PagedList<ListMovie.Response.Result> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<ListMovie.Response.Result>
        ) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(
            params: LoadRangeParams,
            callback: LoadRangeCallback<ListMovie.Response.Result>
        ) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}