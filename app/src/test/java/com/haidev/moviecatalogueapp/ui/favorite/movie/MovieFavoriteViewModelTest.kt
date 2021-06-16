package com.haidev.moviecatalogueapp.ui.favorite.movie

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.haidev.moviecatalogueapp.data.model.DetailMovie
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
class MovieFavoriteViewModelTest {
    @get:Rule
    val textInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var app: Application

    @Mock
    private lateinit var viewModel: MovieFavoriteViewModel

    @Mock
    private lateinit var navigator: MovieFavoriteNavigator

    @Mock
    private lateinit var repo: ApiRepository

    private val dummyMovies = DataDummy.generateDummyDetailMovie()

    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel =
            MovieFavoriteViewModel(
                repo,
                app
            )
        viewModel.navigator = navigator
    }

    @Test
    fun `getMovies Favorite should be success`() {
        testCoroutineRule.runBlockingTest {
            val expected = MutableLiveData<List<DetailMovie.Response?>>()
            expected.value = DataDummy.generateDummyDetailMovie()
            Mockito.`when`(viewModel.getAllFavoriteMovie()).thenReturn(expected)

            val dataMovies = LiveDataTestUtil.getValue(repo.getAllMovieFavorite())
            verify(repo).getAllMovieFavorite()
            Assert.assertNotNull(dataMovies)
            Assert.assertEquals(dummyMovies.size.toLong(), dataMovies.size.toLong())
        }
    }
}