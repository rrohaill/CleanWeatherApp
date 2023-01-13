package io.rrohaill.cleanweatherapp.domain.usecase

import io.rrohaill.cleanweatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class FetchWeatherUseCaseImpl @Inject constructor(private val weatherRepository: WeatherRepository) :
    FetchWeatherUseCase {

    override suspend fun invoke(lat: Double, lon: Double) {
        weatherRepository.fetchWeather(lat = lat, lon = lon)
    }

}