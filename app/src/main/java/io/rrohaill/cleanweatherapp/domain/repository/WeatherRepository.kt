package io.rrohaill.cleanweatherapp.domain.repository

import io.rrohaill.cleanweatherapp.domain.model.WeatherResult
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun fetchWeather(lat: Double, lon: Double)

    fun getWeatherResult(): Flow<WeatherResult>
}