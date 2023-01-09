package io.rrohaill.cleanweatherapp.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.rrohaill.cleanweatherapp.domain.usecase.FetchWeatherUseCase
import io.rrohaill.cleanweatherapp.domain.usecase.GetWeatherUseCase
import io.rrohaill.cleanweatherapp.domain.usecase.model.WeatherUIResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchWeatherUseCase: FetchWeatherUseCase,
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchWeatherUseCase(lat, lon)
        }
    }

    fun getResult(): Flow<WeatherUIResult> = getWeatherUseCase()

}