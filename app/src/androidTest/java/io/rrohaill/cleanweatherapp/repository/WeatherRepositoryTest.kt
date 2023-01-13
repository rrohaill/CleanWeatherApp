package io.rrohaill.cleanweatherapp.repository

import androidx.test.platform.app.InstrumentationRegistry
import io.rrohaill.cleanweatherapp.base.network.ApiResult
import io.rrohaill.cleanweatherapp.base.network.RetrofitFactory
import io.rrohaill.cleanweatherapp.data.WeatherDataSource
import io.rrohaill.cleanweatherapp.data.WeatherDataSourceImpl
import io.rrohaill.cleanweatherapp.test.BuildConfig
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.create

class WeatherRepositoryTest {

    @Test
    fun testFetchWeatherDetail() = runBlocking {
        val result = getWeatherDataSource().fetchWeatherInfo(lat = 57.67, lon = 12.06)

        assert(result is ApiResult.Success && result.data.name == "Mölndal")
    }

    private fun getWeatherDataSource(): WeatherDataSource = WeatherDataSourceImpl(
        RetrofitFactory.retrofit(
            baseUrl = BuildConfig.BASE_URL,
            context = InstrumentationRegistry.getInstrumentation().targetContext
        ).create()
    )

}