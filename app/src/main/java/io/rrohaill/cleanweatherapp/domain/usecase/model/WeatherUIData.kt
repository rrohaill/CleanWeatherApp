package io.rrohaill.cleanweatherapp.domain.usecase.model

data class WeatherUIData(
    val description: String,
    val icon: String,
    val temperature: String,
    val feelsLike: String,
    val min: Double,
    val max: Double,
    val pressure: Int,
    val humidity: Int,
    val clouds: Int,
    val dateTime: String,
    val sunRise: String,
    val sunSet: String,
    val country: String,
    val city: String,
    val windSpeed:Double
)
