package io.rrohaill.cleanweatherapp.usecase.model

sealed class WeatherUIResult {
    data class Success(val data: WeatherUIData) : WeatherUIResult()
    data class Error(val errorMessage: String) : WeatherUIResult()
    object Loading : WeatherUIResult()
}
