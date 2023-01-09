package io.rrohaill.cleanweatherapp.domain.model

import io.rrohaill.cleanweatherapp.data.model.WeatherResponse

sealed class WeatherResult{
    data class Success(val data: WeatherResponse) : WeatherResult()
    data class Error(val errorMessage: String) : WeatherResult()
}
