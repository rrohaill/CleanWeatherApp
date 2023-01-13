package io.rrohaill.cleanweatherapp.view

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.rrohaill.cleanweatherapp.domain.usecase.model.WeatherUIResult
import io.rrohaill.cleanweatherapp.domain.usecase.toUI
import io.rrohaill.cleanweatherapp.getDummyWeatherResponse
import io.rrohaill.cleanweatherapp.view.home.HomeViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeViewModelTest {

    private val success = WeatherUIResult.Success(data = getDummyWeatherResponse().toUI())
    private val error = WeatherUIResult.Error(errorMessage = "error")
    private val loading = WeatherUIResult.Loading

    private fun getViewModelSuccess(): HomeViewModel {
        val homeViewModel: HomeViewModel = mock {
            onBlocking {
                getResult()
            } doReturn flow { emit(success) }
        }
        return homeViewModel
    }

    private fun getViewModelError(): HomeViewModel {
        val homeViewModel: HomeViewModel = mock {
            onBlocking {
                getResult()
            } doReturn flow { emit(error) }
        }
        return homeViewModel
    }

    private fun getViewModelLoading(): HomeViewModel {
        val homeViewModel: HomeViewModel = mock {
            onBlocking {
                getResult()
            } doReturn flow { emit(loading) }
        }
        return homeViewModel
    }

    @Test
    fun testGetWeatherSuccess() = runBlocking {
        val result = getViewModelSuccess().getResult().first()
        assert(
            result is WeatherUIResult.Success
                    && result == success
                    && result.data.city == success.data.city
        )
    }

    @Test
    fun testGetWeatherError() = runBlocking {
        val result = getViewModelError().getResult()
        assertEquals(result.first(), error)
    }

    @Test
    fun testGetWeatherLoading() = runBlocking {
        val result = getViewModelLoading().getResult()
        assertEquals(result.first(), loading)
    }
}