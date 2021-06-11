package com.haidev.moviecatalogueapp.ui.tvshow

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.haidev.moviecatalogueapp.data.model.DetailTvShow
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
class DetailTvShowViewModelTest {
    @get:Rule
    val textInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var app: Application

    @Mock
    private lateinit var viewModel: DetailTvShowViewModel

    @Mock
    private lateinit var navigator: DetailTvShowNavigator

    @Mock
    private lateinit var apiRepo: ApiRepository

    @Mock
    private lateinit var response: DetailTvShow.Response

    private val dummyTvShow: DetailTvShow.Response = DataDummy.generateDummyDetailTvShow()

    private val error = Error()

    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel =
            DetailTvShowViewModel(
                apiRepo,
                app
            )
        viewModel.navigator = navigator
    }


    @Test
    fun `given success response when get detail tvshow and not if null`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            response = dummyTvShow

            Mockito.`when`(apiRepo.getDetailTvShow("63174"))
                .thenReturn(response)

            viewModel.dataDetailTvShow.observeTest {
                // WHEN
                viewModel.getDetailTvShowAsync("63174")

                // THEN
                Mockito.verify(it).onChanged(Resource.loading())
                Mockito.verify(it).onChanged(Resource.success(response))
                Assert.assertNotNull(viewModel.dataDetailTvShow.value)
                Assert.assertEquals(63174, viewModel.dataDetailTvShow.value?.data?.id)
            }
        }
    }

    @Test
    fun `throw error response when get detail tvshow`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            Mockito.`when`(apiRepo.getDetailTvShow("000000")).thenThrow(error)

            viewModel.dataDetailTvShow.observeTest {
                // WHEN
                viewModel.getDetailTvShowAsync("000000")

                // THEN
                Mockito.verify(it).onChanged(Resource.loading(null))
                Mockito.verify(it)
                    .onChanged(Resource.error(ErrorUtils.getErrorThrowableMsg(error), null, error))
            }
        }
    }
}
