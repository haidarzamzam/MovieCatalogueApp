package com.haidev.moviecatalogueapp.ui.movie

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.haidev.moviecatalogueapp.data.model.ListMovie
import com.haidev.moviecatalogueapp.data.model.Resource
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
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
    private lateinit var apiRepo: ApiRepository

    @Mock
    private lateinit var response: ListMovie.Response

    @Mock
    private lateinit var responseObserver: Observer<Resource<ListMovie.Response>>

    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel =
            MovieViewModel(
                apiRepo,
                app
            )
    }

    @Test
    fun `given success response when get manage list movie`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            Mockito.`when`(apiRepo.getListMovie()).thenReturn(response)

            // WHEN
            viewModel.dataListMovie.observeForever(responseObserver)
            viewModel.getListMovieAsync()

            // THEN
            Mockito.verify(responseObserver).onChanged(Resource.loading(null))
            Mockito.verify(responseObserver).onChanged(Resource.success(response))
            viewModel.dataListMovie.removeObserver(responseObserver)
        }
    }
}