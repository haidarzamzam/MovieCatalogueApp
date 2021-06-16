package com.haidev.moviecatalogueapp.ui.tvshow

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import com.haidev.moviecatalogueapp.data.model.Resource
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.utils.TestCoroutineRule
import com.haidev.moviecatalogueapp.utils.DataDummy
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class TvShowViewModelTest {
    @get:Rule
    val textInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var app: Application

    @Mock
    private lateinit var viewModel: TvShowViewModel

    @Mock
    private lateinit var navigator: TvShowNavigator

    @Mock
    private lateinit var repo: ApiRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<ListTvShow.Response.Result>>>

    private val dummyTvSHow = DataDummy.generateDummyListTvShow()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel =
            TvShowViewModel(
                repo,
                app
            )
        viewModel.navigator = navigator
    }

    @Test
    fun `getTvShows should be success`() {
        testCoroutineRule.runBlockingTest {
            val tvshow = PagedTestDataSources.snapshot(dummyTvSHow)
            val expected = MutableLiveData<Resource<PagedList<ListTvShow.Response.Result>>>()
            expected.value = Resource.success(tvshow)

            Mockito.`when`(repo.getListTvShow()).thenReturn(expected)

            viewModel.getAllListTvShow().observeForever(observer)
            Mockito.verify(observer).onChanged(expected.value)

            val expectedValue = expected.value
            val actualValue = viewModel.getAllListTvShow().value
            Assert.assertEquals(expectedValue, actualValue)
            Assert.assertEquals(expectedValue?.data, actualValue?.data)
            Assert.assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
        }
    }

    @Test
    fun `getTvShows should be success but data is empty`() {
        testCoroutineRule.runBlockingTest {
            val courses = PagedTestDataSources.snapshot()
            val expected = MutableLiveData<Resource<PagedList<ListTvShow.Response.Result>>>()
            expected.value = Resource.success(courses)

            Mockito.`when`(repo.getListTvShow()).thenReturn(expected)

            viewModel.getAllListTvShow().observeForever(observer)
            Mockito.verify(observer).onChanged(expected.value)

            val actualValueDataSize = viewModel.getAllListTvShow().value?.data?.size
            Assert.assertTrue(
                "size of data should be 0, actual is $actualValueDataSize",
                actualValueDataSize == 0
            )
        }
    }

    @Test
    fun `getTvShows should be error`() {
        testCoroutineRule.runBlockingTest {
            val expectedMessage = "Something happen dude!"
            val expected = MutableLiveData<Resource<PagedList<ListTvShow.Response.Result>>>()
            expected.value = Resource.error(expectedMessage, null)

            Mockito.`when`(repo.getListTvShow()).thenReturn(expected)

            viewModel.getAllListTvShow().observeForever(observer)
            Mockito.verify(observer).onChanged(expected.value)

            val actualMessage = viewModel.getAllListTvShow().value?.message
            Assert.assertEquals(expectedMessage, actualMessage)
        }
    }


    class PagedTestDataSources private constructor(private val items: List<ListTvShow.Response.Result>) :
        PositionalDataSource<ListTvShow.Response.Result>() {
        companion object {
            fun snapshot(items: List<ListTvShow.Response.Result> = listOf()): PagedList<ListTvShow.Response.Result> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<ListTvShow.Response.Result>
        ) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(
            params: LoadRangeParams,
            callback: LoadRangeCallback<ListTvShow.Response.Result>
        ) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}