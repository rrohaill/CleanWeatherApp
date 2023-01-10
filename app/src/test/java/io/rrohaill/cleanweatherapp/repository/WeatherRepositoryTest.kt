package io.rrohaill.cleanweatherapp.repository

import com.nhaarman.mockitokotlin2.verify
import io.rrohaill.cleanweatherapp.BuildConfig
import io.rrohaill.cleanweatherapp.base.network.ApiResult
import io.rrohaill.cleanweatherapp.base.network.WeatherApi
import io.rrohaill.cleanweatherapp.data.WeatherDataSource
import io.rrohaill.cleanweatherapp.data.WeatherDataSourceImpl
import io.rrohaill.cleanweatherapp.data.model.WeatherResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response


class WeatherRepositoryTest {

    @Mock
    private lateinit var weatherAPI: WeatherApi

    private lateinit var weatherDataSource: WeatherDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        weatherDataSource = WeatherDataSourceImpl(weatherAPI)
    }

    @Test
    fun testGetData() = runBlocking {
        val expectedData = WeatherResponse(
            "", WeatherResponse.Clouds(7), -1,
            WeatherResponse.Coord(1.0, 2.0), 1234, -1,
            WeatherResponse.Main(-3.0, 2, 3, 1.0, 5.0, 1.0), "",
            WeatherResponse.Sys("", -1, 6, 8, 1), 1234, 5,
            emptyList(), WeatherResponse.Wind(3, 3.0)
        )
        val call = Response.success(expectedData)

        `when`(weatherAPI.getWeatherInfo(1.0, 2.0, BuildConfig.API_KEY)).thenReturn(call)

        val data = weatherDataSource.fetchWeatherInfo(1.0, 2.0)
            .let { if (it is ApiResult.Success) it.data else it }

        verify(weatherAPI).getWeatherInfo(1.0, 2.0, BuildConfig.API_KEY)
        assertEquals(expectedData, data)
    }
}