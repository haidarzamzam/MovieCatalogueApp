package com.haidev.moviecatalogueapp.ui.favorite.tvshow

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.utils.LiveDataTestUtil
import com.haidev.moviecatalogueapp.ui.utils.TestCoroutineRule
import com.haidev.moviecatalogueapp.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
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
class TvShowFavoriteViewModelTest {
    @get:Rule
    val textInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var app: Application

    @Mock
    private lateinit var viewModel: TvShowFavoriteViewModel

    @Mock
    private lateinit var navigator: TvShowFavoriteNavigator

    @Mock
    private lateinit var repo: ApiRepository

    private val dummyTvShow = DataDummy.generateDummyDetailTvShow()

    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel =
            TvShowFavoriteViewModel(
                repo,
                app
            )
        viewModel.navigator = navigator
    }

    @Test
    fun `getTvShow Favorite should be success`() {
        testCoroutineRule.runBlockingTest {
            val expected = MutableLiveData<List<DetailTvShow.Response?>>()
            expected.value = DataDummy.generateDummyDetailTvShow()
            Mockito.`when`(viewModel.getAllFavoriteTvSHow()).thenReturn(expected)

            val dataTvShows = LiveDataTestUtil.getValue(repo.getAllTvShowFavorite())
            verify(repo).getAllTvShowFavorite()
            Assert.assertNotNull(dataTvShows)
            Assert.assertEquals(dummyTvShow.size.toLong(), dataTvShows.size.toLong())
        }
    }
}