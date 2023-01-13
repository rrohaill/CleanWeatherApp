package io.rrohaill.cleanweatherapp.domain.usecase

interface FetchWeatherUseCase {
    suspend operator fun invoke(lat: Double, lon: Double)
}