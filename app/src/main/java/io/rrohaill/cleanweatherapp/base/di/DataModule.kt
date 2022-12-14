package io.rrohaill.cleanweatherapp.base.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.rrohaill.cleanweatherapp.data.WeatherDataSource
import io.rrohaill.cleanweatherapp.data.WeatherDataSourceImpl
import io.rrohaill.cleanweatherapp.data.WeatherRepositoryImpl
import io.rrohaill.cleanweatherapp.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    @Binds
    @Singleton
    abstract fun provideWeatherDataSource(impl: WeatherDataSourceImpl): WeatherDataSource
}