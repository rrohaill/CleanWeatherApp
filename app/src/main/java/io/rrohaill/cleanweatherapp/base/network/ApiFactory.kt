package io.rrohaill.cleanweatherapp.base.network

import android.content.Context
import io.rrohaill.cleanweatherapp.BuildConfig
import retrofit2.create

object ApiFactory {

    fun getApi(context: Context): WeatherApi =
        RetrofitFactory.retrofit(
            baseUrl = BuildConfig.BASE_URL,
            context = context
        ).create()
}