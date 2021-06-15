package com.haidev.moviecatalogueapp.ui.tvshow

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import com.haidev.moviecatalogueapp.data.model.Resource
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.utils.PagedListUtil
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
    fun getAllMovies() {
        testCoroutineRule.runBlockingTest {
            val dataSourceFactory =
                Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ListTvShow.Response.Result>
            Mockito.`when`(repo.getAllTvShow()).thenReturn(dataSourceFactory)
            repo.getListTvShow()

            val movieEntities =
                Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyListTvShow()))
            Assert.assertNotNull(movieEntities.data)
            Assert.assertEquals(
                dummyTvSHow.size.toLong(),
                movieEntities.data?.size?.toLong()
            )
        }
    }
}