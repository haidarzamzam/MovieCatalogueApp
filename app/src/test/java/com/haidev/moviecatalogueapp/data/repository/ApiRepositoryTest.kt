package com.haidev.moviecatalogueapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import com.haidev.moviecatalogueapp.data.source.dao.MovieDao
import com.haidev.moviecatalogueapp.data.source.dao.TvShowDao
import com.haidev.moviecatalogueapp.data.source.endpoint.ApiService
import com.haidev.moviecatalogueapp.ui.utils.TestCoroutineRule
import com.haidev.moviecatalogueapp.utils.ContextProviders
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
    private lateinit var responseMovies: List<ListMovie.Response.Result>

    @Mock
    private lateinit var responseTvShow: List<ListTvShow.Response.Result>

    @Mock
    private lateinit var responseDetailMovies: DetailMovie.Response

    @Mock
    private lateinit var responseDetailTvShow: DetailTvShow.Response

    @Mock
    private val apiService: ApiService? = null

    @Mock
    private lateinit var movieDao: MovieDao

    @Mock
    private lateinit var tvShowDao: TvShowDao

    @Mock
    private lateinit var coroutineContext: ContextProviders

    private var apiRepo =
        apiService?.let { FakeApiRepository(it, movieDao, tvShowDao, coroutineContext) }

    private val dummyMovies = DataDummy.generateDummyListMovie()
    private val dummyTvShow = DataDummy.generateDummyListTvShow()
    private val dummyDetailMovies: DetailMovie.Response = DataDummy.generateDummyDetailMovie()
    private val dummyDetailTvShow: DetailTvShow.Response = DataDummy.generateDummyDetailTvShow()

    @Mock
    private lateinit var repo: ApiRepository

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
            Mockito.`when`(repo.getAllMovie()).thenReturn(dataSourceFactory)
            apiRepo?.getListMovie()

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
            Mockito.`when`(repo.getAllTvShow()).thenReturn(dataSourceFactory)
            apiRepo?.getListTvShow()

            assertNotNull(responseTvShow)
            assertEquals(1, responseTvShow.size)
        }
    }

    @Test
    fun getDetailMovie() {
        testCoroutineRule.runBlockingTest {
            responseDetailMovies = dummyDetailMovies

            Mockito.`when`(repo.getDetailMovie("337404"))
                .thenReturn(responseDetailMovies)

            assertNotNull(responseDetailMovies)
            assertEquals(337404, responseDetailMovies.id)
        }
    }

    @Test
    fun getDetailTvShow() {
        testCoroutineRule.runBlockingTest {
            responseDetailTvShow = dummyDetailTvShow

            Mockito.`when`(repo.getDetailTvShow("63174"))
                .thenReturn(responseDetailTvShow)

            assertNotNull(responseDetailTvShow)
            assertEquals(63174, responseDetailTvShow.id)
        }
    }
}