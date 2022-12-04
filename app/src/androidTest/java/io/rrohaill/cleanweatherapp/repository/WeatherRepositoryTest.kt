package io.rrohaill.cleanweatherapp.repository

import androidx.test.platform.app.InstrumentationRegistry
import io.rrohaill.cleanweatherapp.base.network.ApiFactory
import io.rrohaill.cleanweatherapp.base.network.ApiResult
import io.rrohaill.cleanweatherapp.data.WeatherDataSource
import io.rrohaill.cleanweatherapp.data.WeatherDataSourceImpl
import kotlinx.coroutines.runBlocking
import org.junit.Test

class WeatherRepositoryTest {

    @Test
    fun testFetchWeatherDetail() = runBlocking {
        val result = getWeatherDataSource().fetchWeatherInfo(lat = 57.67, lon = 12.06)

        assert(result is ApiResult.Success && result.data.name == "Mölndal")
    }

    private fun getWeatherDataSource(): WeatherDataSource = WeatherDataSourceImpl(
        ApiFactory.getApi(
            InstrumentationRegistry.getInstrumentation().targetContext
        )
    )

}