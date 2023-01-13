package io.rrohaill.cleanweatherapp.domain.usecase

import io.rrohaill.cleanweatherapp.domain.usecase.model.WeatherUIResult
import kotlinx.coroutines.flow.Flow

interface GetWeatherUseCase {
    operator fun invoke(): Flow<WeatherUIResult>
}