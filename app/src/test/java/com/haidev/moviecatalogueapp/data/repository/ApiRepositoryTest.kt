package com.haidev.moviecatalogueapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import com.haidev.moviecatalogueapp.ui.utils.TestCoroutineRule
import com.haidev.moviecatalogueapp.utils.DataDummy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ApiRepositoryTest {
    @get:Rule
    val textInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiRepo: ApiRepository

    @Mock
    private lateinit var responseMovies: List<ListMovie.Response.Result>

    @Mock
    private lateinit var responseTvShow: List<ListTvShow.Response.Result>

    @Mock
    private lateinit var responseDetailMovies: DetailMovie.Response

    @Mock
    private lateinit var responseDetailTvShow: DetailTvShow.Response

    private val dummyMovies = DataDummy.generateDummyListMovie()
    private val dummyTvShow = DataDummy.generateDummyListTvShow()
    private val dummyDetailMovies: DetailMovie.Response = DataDummy.generateDummyDetailMovie()
    private val dummyDetailTvShow: DetailTvShow.Response = DataDummy.generateDummyDetailTvShow()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getListMovie() {
        testCoroutineRule.runBlockingTest {
            responseMovies = dummyMovies

            val dataSourceFactory =
                Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ListMovie.Response.Result>
            Mockito.`when`(apiRepo.getAllMovie()).thenReturn(dataSourceFactory)

            assertNotNull(responseMovies)
            assertEquals(1, responseMovies.size)
        }
    }

    @Test
    fun getListTvShow() {
        testCoroutineRule.runBlockingTest {
            responseTvShow = dummyTvShow

            val dataSourceFactory =
                Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ListTvShow.Response.Result>
            Mockito.`when`(apiRepo.getAllTvShow()).thenReturn(dataSourceFactory)

            assertNotNull(responseTvShow)
            assertEquals(1, responseTvShow.size)
        }
    }

    @Test
    fun getDetailMovie() {
        testCoroutineRule.runBlockingTest {
            responseDetailMovies = dummyDetailMovies

            Mockito.`when`(apiRepo.getDetailMovie("337404"))
                .thenReturn(responseDetailMovies)

            assertNotNull(responseDetailMovies)
            assertEquals(337404, responseDetailMovies.id)
        }
    }

    @Test
    fun getDetailTvShow() {
        testCoroutineRule.runBlockingTest {
            responseDetailTvShow = dummyDetailTvShow

            Mockito.`when`(apiRepo.getDetailTvShow("63174"))
                .thenReturn(responseDetailTvShow)

            assertNotNull(responseDetailTvShow)
            assertEquals(63174, responseDetailTvShow.id)
        }
    }
}