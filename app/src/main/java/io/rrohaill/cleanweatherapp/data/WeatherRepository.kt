package io.rohail.metaweatherapp.dashboard.data

import io.rrohaill.cleanweatherapp.base.network.ApiResult
import io.rrohaill.cleanweatherapp.data.WeatherDataSource
import io.rrohaill.cleanweatherapp.data.model.WeatherResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

interface WeatherRepository {

    suspend fun fetchWeather(lat: Double, lon: Double)

    fun getWeatherResult(): Flow<WeatherResult>
}

class WeatherRepositoryImpl @Inject constructor(private val weatherDataSource: WeatherDataSource) :
    WeatherRepository {

    private val weatherResult = MutableSharedFlow<WeatherResult>()

    override suspend fun fetchWeather(lat: Double, lon: Double) {
        weatherDataSource.fetchWeatherInfo(lat = lat, lon = lon).let { result ->
            when (result) {
                is ApiResult.Success -> weatherResult.emit(
                    WeatherResult.Success(data = result.data)
                )
                is ApiResult.Error -> weatherResult.emit(
                    WeatherResult.Error(errorMessage = result.message)
                )
            }
        }
    }

    override fun getWeatherResult(): Flow<WeatherResult> = weatherResult

}