package io.rrohaill.cleanweatherapp.usecase

import io.rohail.metaweatherapp.dashboard.data.WeatherRepository
import javax.inject.Inject

interface FetchWeatherUseCase {
    suspend operator fun invoke(lat: Double, lon: Double)
}

class FetchWeatherUseCaseImpl @Inject constructor(private val weatherRepository: WeatherRepository) :
    FetchWeatherUseCase {

    override suspend fun invoke(lat: Double, lon: Double) {
        weatherRepository.fetchWeather(lat = lat, lon = lon)
    }

}