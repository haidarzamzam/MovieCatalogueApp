package com.haidev.moviecatalogueapp.ui.movie

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.haidev.moviecatalogueapp.data.model.DetailMovie
import com.haidev.moviecatalogueapp.data.model.Resource
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.utils.TestCoroutineRule
import com.haidev.moviecatalogueapp.ui.utils.observeTest
import com.haidev.moviecatalogueapp.utils.DataDummy
import com.haidev.moviecatalogueapp.utils.ErrorUtils
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
class DetailMovieViewModelTest {
    @get:Rule
    val textInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var app: Application

    @Mock
    private lateinit var viewModel: DetailMovieViewModel

    @Mock
    private lateinit var navigator: DetailMovieNavigator

    @Mock
    private lateinit var apiRepo: ApiRepository

    @Mock
    private lateinit var response: DetailMovie.Response

    private val dummyMovies: DetailMovie.Response = DataDummy.generateDummyDetailMovie()

    private val error = Error()

    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel =
            DetailMovieViewModel(
                apiRepo,
                app
            )
        viewModel.navigator = navigator
    }


    @Test
    fun `given success response when get detail movie and not if null`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            response = dummyMovies

            Mockito.`when`(apiRepo.getDetailMovie("337404"))
                .thenReturn(response)

            viewModel.dataDetailMovie.observeTest {
                // WHEN
                viewModel.getDetailMovieAsync("337404")

                // THEN
                Mockito.verify(it).onChanged(Resource.loading())
                Mockito.verify(it).onChanged(Resource.success(response))
                Assert.assertNotNull(viewModel.dataDetailMovie.value)
                Assert.assertEquals(337404, viewModel.dataDetailMovie.value?.data?.id)
            }
        }
    }

    @Test
    fun `throw error response when get detail movie`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            Mockito.`when`(apiRepo.getDetailMovie("000000")).thenThrow(error)

            viewModel.dataDetailMovie.observeTest {
                // WHEN
                viewModel.getDetailMovieAsync("000000")

                // THEN
                Mockito.verify(it).onChanged(Resource.loading(null))
                Mockito.verify(it)
                    .onChanged(Resource.error(ErrorUtils.getErrorThrowableMsg(error), null, error))
            }
        }
    }
}