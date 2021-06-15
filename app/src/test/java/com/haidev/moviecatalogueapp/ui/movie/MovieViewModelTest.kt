package com.haidev.moviecatalogueapp.ui.movie

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.data.model.Resource
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.utils.PagedListUtil
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
    fun getAllMovies() {
        testCoroutineRule.runBlockingTest {
            val dataSourceFactory =
                Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ListMovie.Response.Result>
            Mockito.`when`(repo.getAllMovie()).thenReturn(dataSourceFactory)
            repo.getListMovie()

            val movieEntities =
                Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyListMovie()))
            Assert.assertNotNull(movieEntities.data)
            assertEquals(dummyMovies.size.toLong(), movieEntities.data?.size?.toLong())
        }
    }
}