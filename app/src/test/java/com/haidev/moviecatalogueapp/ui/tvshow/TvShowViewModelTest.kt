package com.haidev.moviecatalogueapp.ui.tvshow

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.haidev.moviecatalogueapp.data.model.ListTvShow
import com.haidev.moviecatalogueapp.data.model.Resource
import com.haidev.moviecatalogueapp.data.repository.ApiRepository
import com.haidev.moviecatalogueapp.ui.utils.TestCoroutineRule
import com.haidev.moviecatalogueapp.ui.utils.observeTest
import com.haidev.moviecatalogueapp.utils.DataDummy
import com.haidev.moviecatalogueapp.utils.ErrorUtils
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
    private lateinit var apiRepo: ApiRepository

    @Mock
    private lateinit var response: ListTvShow.Response

    private val error = Error()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel =
            TvShowViewModel(
                apiRepo,
                app
            )
        viewModel.navigator = navigator
    }

    @Test
    fun `given success response when get list movie`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            val dummyTvSHow: ListTvShow.Response = DataDummy.generateDummyListTvShow()
            response = dummyTvSHow

            Mockito.`when`(apiRepo.getListTvShow())
                .thenReturn(response)

            viewModel.dataListTvShow.observeTest {
                // WHEN
                viewModel.getListTvShowAsync()

                // THEN
                Assert.assertNotNull(viewModel.dataListTvShow.value)
                Assert.assertEquals(1, viewModel.dataListTvShow.value?.data?.results?.size)
                Mockito.verify(it).onChanged(Resource.loading())
                Mockito.verify(it).onChanged(Resource.success(response))
            }
        }
    }

    @Test
    fun `throw error response when get list movie`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            Mockito.`when`(apiRepo.getListTvShow()).thenThrow(error)

            viewModel.dataListTvShow.observeTest {
                // WHEN
                viewModel.getListTvShowAsync()

                // THEN
                Mockito.verify(it).onChanged(Resource.loading(null))
                Mockito.verify(it)
                    .onChanged(
                        Resource.error(
                            ErrorUtils.getErrorThrowableMsg(error), null, error
                        )
                    )
            }
        }
    }
}