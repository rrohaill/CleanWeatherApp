package io.rrohaill.cleanweatherapp.view

import androidx.test.platform.app.InstrumentationRegistry
import io.rrohaill.cleanweatherapp.base.network.ApiFactory
import io.rrohaill.cleanweatherapp.data.WeatherDataSourceImpl
import io.rrohaill.cleanweatherapp.data.WeatherRepositoryImpl
import io.rrohaill.cleanweatherapp.domain.repository.WeatherRepository
import io.rrohaill.cleanweatherapp.domain.usecase.FetchWeatherUseCase
import io.rrohaill.cleanweatherapp.domain.usecase.FetchWeatherUseCaseImpl
import io.rrohaill.cleanweatherapp.domain.usecase.GetWeatherUseCase
import io.rrohaill.cleanweatherapp.domain.usecase.GetWeatherUseCaseImpl
import io.rrohaill.cleanweatherapp.domain.usecase.model.WeatherUIResult
import io.rrohaill.cleanweatherapp.view.home.HomeViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test


class HomeViewModelAndroidTest {

    private val repository = getWeatherRepository()
    private val fetchWeatherUseCase: FetchWeatherUseCase =
        FetchWeatherUseCaseImpl(repository)
    private val getWeatherUseCase: GetWeatherUseCase = GetWeatherUseCaseImpl(repository)
    private val viewModel = HomeViewModel(
        fetchWeatherUseCase = fetchWeatherUseCase,
        getWeatherUseCase = getWeatherUseCase
    )

    @Test
    fun fetchWeatherResult() = runBlocking {

        viewModel.fetchWeather(lat = 32.06, 12.26)

        assert(viewModel.getResult().first() is WeatherUIResult.Success)
    }

    private fun getWeatherRepository(): WeatherRepository = WeatherRepositoryImpl(
        WeatherDataSourceImpl(
            ApiFactory.getApi(
                InstrumentationRegistry.getInstrumentation().targetContext
            )
        )
    )
}