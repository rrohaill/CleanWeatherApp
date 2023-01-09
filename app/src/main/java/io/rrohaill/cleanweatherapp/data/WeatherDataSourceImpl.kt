package io.rrohaill.cleanweatherapp.data

import io.rrohaill.cleanweatherapp.BuildConfig
import io.rrohaill.cleanweatherapp.base.network.ApiResult
import io.rrohaill.cleanweatherapp.base.network.BaseDataSource
import io.rrohaill.cleanweatherapp.base.network.WeatherApi
import io.rrohaill.cleanweatherapp.data.model.WeatherResponse
import javax.inject.Inject

class WeatherDataSourceImpl @Inject constructor(private val service: WeatherApi) : BaseDataSource(),
    WeatherDataSource {

    override suspend fun fetchWeatherInfo(lat: Double, lon: Double): ApiResult<WeatherResponse> =
        getResult {
            service.getWeatherInfo(lat = lat, lon = lon, appid = BuildConfig.API_KEY)
        }

}