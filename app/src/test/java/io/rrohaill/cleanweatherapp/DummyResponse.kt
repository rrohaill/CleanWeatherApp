package io.rrohaill.cleanweatherapp

import io.rrohaill.cleanweatherapp.data.model.WeatherResponse

/** Instantiates a dummy test object for [WeatherResponse].
 * @return The dummy test object. */
fun getDummyWeatherResponse(): WeatherResponse =
    WeatherResponse(
        base = "",
        clouds = WeatherResponse.Clouds(0),
        cod = 0,
        coord = WeatherResponse.Coord(0.0, 0.0),
        0,
        0,
        main = WeatherResponse.Main(0.0, 0, 0, 0.0, 0.0, 0.0),
        name = "",
        sys = WeatherResponse.Sys("", 0, 0, 0, 0),
        timezone = 0,
        visibility = 0,
        weather = emptyList(),
        wind = WeatherResponse.Wind(0, 0.0)
    )