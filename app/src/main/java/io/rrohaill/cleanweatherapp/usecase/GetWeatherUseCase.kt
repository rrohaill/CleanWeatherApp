package io.rrohaill.cleanweatherapp.usecase

import io.rohail.metaweatherapp.dashboard.data.WeatherRepository
import io.rrohaill.cleanweatherapp.data.model.WeatherResponse
import io.rrohaill.cleanweatherapp.data.model.WeatherResult
import io.rrohaill.cleanweatherapp.usecase.model.WeatherUIData
import io.rrohaill.cleanweatherapp.usecase.model.WeatherUIResult
import io.rrohaill.cleanweatherapp.utils.DateFormat
import io.rrohaill.cleanweatherapp.utils.toDateTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetWeatherUseCase {
    operator fun invoke(): Flow<WeatherUIResult>
}

class GetWeatherUseCaseImpl @Inject constructor(private val weatherRepository: WeatherRepository) :
    GetWeatherUseCase {
    override fun invoke(): Flow<WeatherUIResult> =
        weatherRepository.getWeatherResult().map { result ->
            when (result) {
                is WeatherResult.Success ->
                    WeatherUIResult.Success(result.data.toUI())
                is WeatherResult.Error -> WeatherUIResult.Error(errorMessage = result.errorMessage)
            }
        }

}

fun WeatherResponse.toUI(): WeatherUIData {
    val weather = weather.firstOrNull()
    return WeatherUIData(
        description = weather?.description ?: "",
        icon = weather?.icon ?: "",
        temperature = main.temp.toString(),
        feelsLike = main.feelsLike.toString(),
        min = main.tempMin,
        max = main.tempMax,
        pressure = main.pressure,
        humidity = main.humidity,
        clouds = clouds.all,
        dateTime = dt.toDateTime(DateFormat.HH_mm),
        sunRise = sys.sunrise.toDateTime(DateFormat.HH_mm),
        sunSet = sys.sunset.toDateTime(DateFormat.HH_mm),
        country = sys.country,
        city = name,
        windSpeed = wind.speed
    )
}