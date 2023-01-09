package io.rrohaill.cleanweatherapp.data

import io.rrohaill.cleanweatherapp.base.network.ApiResult
import io.rrohaill.cleanweatherapp.data.model.WeatherResponse

interface WeatherDataSource {
    suspend fun fetchWeatherInfo(lat: Double, lon: Double): ApiResult<WeatherResponse>
}