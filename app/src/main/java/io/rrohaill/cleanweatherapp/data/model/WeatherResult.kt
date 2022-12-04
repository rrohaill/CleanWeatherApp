package io.rrohaill.cleanweatherapp.data.model

sealed class WeatherResult{
    data class Success(val data: WeatherResponse) : WeatherResult()
    data class Error(val errorMessage: String) : WeatherResult()
}
