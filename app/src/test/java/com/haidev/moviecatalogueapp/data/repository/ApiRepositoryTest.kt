package com.haidev.moviecatalogueapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.data.model.Resource
import com.haidev.moviecatalogueapp.data.source.dao.MovieDao
import com.haidev.moviecatalogueapp.data.source.dao.TvShowDao
import com.haidev.moviecatalogueapp.data.source.endpoint.ApiService
import com.haidev.moviecatalogueapp.ui.utils.PagedListUtil
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
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ApiRepositoryTest {
    @get:Rule
    val textInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private val apiService: ApiService? = null

    @Mock
    private lateinit var movieDao: MovieDao

    @Mock
    private lateinit var tvShowDao: TvShowDao

    @Mock
    private lateinit var coroutineContext: ContextProviders

    private var repo =
        apiService?.let { FakeApiRepository(it, movieDao, tvShowDao, coroutineContext) }

    private val dummyMovies = DataDummy.generateDummyListMovie()
    private val dummyTvShow = DataDummy.generateDummyListTvShow()
    private val dummyDetailMovies: DetailMovie.Response =
        DataDummy.generateDummyDetailMovie().first()
    private val dummyDetailTvShow: DetailTvShow.Response =
        DataDummy.generateDummyDetailTvShow().first()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getListMovie() {
        testCoroutineRule.runBlockingTest {
            repo?.getListMovie()

            val movieEntity = Resource.success(PagedListUtil.mockPagedList(dummyTvShow))
            repo?.getAllMovie()
            assertNotNull(movieEntity.data)
            assertEquals(dummyMovies.size.toLong(), movieEntity.data?.size?.toLong())
        }
    }

    @Test
    fun getListTvShow() {
        testCoroutineRule.runBlockingTest {
            repo?.getListTvShow()

            val tvShowEntity = Resource.success(PagedListUtil.mockPagedList(dummyTvShow))
            repo?.getAllTvShow()
            assertNotNull(tvShowEntity.data)
            assertEquals(dummyTvShow.size.toLong(), tvShowEntity.data?.size?.toLong())
        }
    }

    @Test
    fun getDetailMovie() {
        testCoroutineRule.runBlockingTest {
            repo?.getDetailMovie("337404")

            val detailMovie =
                Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyDetailMovie()))
            assertNotNull(detailMovie.data)
            assertEquals(dummyDetailMovies.id, detailMovie.data?.first()?.id)
        }
    }

    @Test
    fun getDetailTvShow() {
        testCoroutineRule.runBlockingTest {
            repo?.getDetailTvShow("63174")

            val detailTvShow =
                Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyDetailTvShow()))
            assertNotNull(detailTvShow.data)
            assertEquals(dummyDetailTvShow.id, detailTvShow.data?.first()?.id)
        }
    }

    @Test
    fun getDataAllFavoriteMovie() {
        testCoroutineRule.runBlockingTest {
            repo?.getAllMovieFavorite()

            val listFavoriteMovie =
                Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyDetailMovie()))
            assertNotNull(listFavoriteMovie.data)
            assertEquals(dummyDetailMovies, listFavoriteMovie.data?.first())
        }
    }

    @Test
    fun getDataAllFavoriteTvMovie() {
        testCoroutineRule.runBlockingTest {
            repo?.getAllTvShowFavorite()

            val listFavoriteTvShow =
                Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyDetailTvShow()))
            assertNotNull(listFavoriteTvShow.data)
            assertEquals(dummyDetailTvShow, listFavoriteTvShow.data?.first())
        }
    }

    @Test
    fun getDataFavoriteMovieById() {
        testCoroutineRule.runBlockingTest {
            repo?.getMovieFavorite(337404)

            val favoriteMovie =
                Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyDetailMovie()))
            assertNotNull(favoriteMovie.data)
            assertEquals(dummyDetailMovies.id, favoriteMovie.data?.first()?.id)
        }
    }

    @Test
    fun getDataFavoriteTvShowById() {
        testCoroutineRule.runBlockingTest {
            repo?.getTvShowFavorite(63174)

            val favoriteTvShow =
                Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyDetailTvShow()))
            assertNotNull(favoriteTvShow.data)
            assertEquals(dummyDetailTvShow.id, favoriteTvShow.data?.first()?.id)
        }
    }

}