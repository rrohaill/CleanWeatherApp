package io.rrohaill.cleanweatherapp.domain.usecase

import io.rrohaill.cleanweatherapp.data.model.WeatherResponse
import io.rrohaill.cleanweatherapp.domain.model.WeatherResult
import io.rrohaill.cleanweatherapp.domain.repository.WeatherRepository
import io.rrohaill.cleanweatherapp.domain.usecase.model.WeatherUIData
import io.rrohaill.cleanweatherapp.domain.usecase.model.WeatherUIResult
import io.rrohaill.cleanweatherapp.common.utils.DateFormat
import io.rrohaill.cleanweatherapp.common.utils.toDateTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

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

/**
 * Mapper for UI related Data
 *
 * The number in time is unix time - time in seconds that elapsed since 1970.
 * Multiply it by 1000 because it is time in seconds and Date constructor takes milliseconds.
 */
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
        dateTime = (dt * 1000).toDateTime(DateFormat.HH_mm),
        sunRise = (sys.sunrise * 1000).toDateTime(DateFormat.HH_mm),
        sunSet = (sys.sunset * 1000).toDateTime(DateFormat.HH_mm),
        country = sys.country,
        city = name,
        windSpeed = wind.speed
    )
}