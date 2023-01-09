package io.rrohaill.cleanweatherapp.data

import io.rrohaill.cleanweatherapp.base.network.ApiResult
import io.rrohaill.cleanweatherapp.domain.model.WeatherResult
import io.rrohaill.cleanweatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

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