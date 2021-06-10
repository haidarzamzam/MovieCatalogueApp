package com.haidev.moviecatalogueapp.ui.movie

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.haidev.moviecatalogueapp.data.model.ListMovie
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
    private lateinit var apiRepo: ApiRepository

    @Mock
    private lateinit var response: ListMovie.Response

    private val dummyMovies: ListMovie.Response = DataDummy.generateDummyListMovie()

    private val error = Error()

    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel =
            MovieViewModel(
                apiRepo,
                app
            )
        viewModel.navigator = navigator
    }


    @Test
    fun `given success response when get list movie and not if null`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            response = dummyMovies

            Mockito.`when`(apiRepo.getListMovie())
                .thenReturn(response)

            viewModel.dataListMovie.observeTest {
                // WHEN
                viewModel.getListMovieAsync()

                // THEN
                Mockito.verify(it).onChanged(Resource.loading())
                Mockito.verify(it).onChanged(Resource.success(response))
                Assert.assertNotNull(viewModel.dataListMovie.value)
                Assert.assertEquals(1, viewModel.dataListMovie.value?.data?.results?.size)
            }
        }
    }

    @Test
    fun `throw error response when get list movie`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            Mockito.`when`(apiRepo.getListMovie()).thenThrow(error)

            viewModel.dataListMovie.observeTest {
                // WHEN
                viewModel.getListMovieAsync()

                // THEN
                Mockito.verify(it).onChanged(Resource.loading(null))
                Mockito.verify(it)
                    .onChanged(Resource.error(ErrorUtils.getErrorThrowableMsg(error), null, error))
            }
        }
    }
}