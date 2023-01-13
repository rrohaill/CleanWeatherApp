package io.rrohaill.cleanweatherapp.usecase

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.rrohaill.cleanweatherapp.domain.model.WeatherResult
import io.rrohaill.cleanweatherapp.domain.repository.WeatherRepository
import io.rrohaill.cleanweatherapp.domain.usecase.GetWeatherUseCaseImpl
import io.rrohaill.cleanweatherapp.domain.usecase.model.WeatherUIData
import io.rrohaill.cleanweatherapp.domain.usecase.model.WeatherUIResult
import io.rrohaill.cleanweatherapp.domain.usecase.toUI
import io.rrohaill.cleanweatherapp.getDummyWeatherResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherUseCaseTest {

    private val successResult = WeatherResult.Success(
        data = getDummyWeatherResponse()
    )
    private val errorResult = WeatherResult.Error("Error")

    private val successUiResult = WeatherUIResult.Success(getDummyWeatherUI())
    private val errorUiResult = WeatherUIResult.Error("Error")


    @Test
    fun `get weather ui result success`() = runBlocking {
        val uc = GetWeatherUseCaseImpl(weatherRepository = getWeatherRepoMock(true))

        val result = uc().first()
        assertEquals(successUiResult, result)
        assert(result is WeatherUIResult.Success && result.data == successUiResult.data)
    }

    @Test
    fun `get weather ui result error`() = runBlocking {
        val uc = GetWeatherUseCaseImpl(weatherRepository = getWeatherRepoMock(false))

        assertEquals(errorUiResult, uc().first())
    }

    private fun getWeatherRepoMock(hasValue: Boolean): WeatherRepository = mock {
        onBlocking {
            getWeatherResult()
        } doReturn if (hasValue) flow { emit(successResult) } else flow { emit(errorResult) }
    }


    /** Instantiates a dummy test object for [WeatherUIData].
     * @return The dummy test object. */
    private fun getDummyWeatherUI(): WeatherUIData =
        getDummyWeatherResponse().toUI()
}