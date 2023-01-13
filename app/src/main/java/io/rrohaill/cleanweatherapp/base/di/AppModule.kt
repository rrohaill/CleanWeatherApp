package io.rrohaill.cleanweatherapp.base.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.rrohaill.cleanweatherapp.BuildConfig
import io.rrohaill.cleanweatherapp.base.network.RetrofitFactory
import io.rrohaill.cleanweatherapp.base.network.WeatherApi
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApi(@ApplicationContext appContext: Context): WeatherApi =
        RetrofitFactory.retrofit(
            baseUrl = BuildConfig.BASE_URL,
            context = appContext
        ).create()
}