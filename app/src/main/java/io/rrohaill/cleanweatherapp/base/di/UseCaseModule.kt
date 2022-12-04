package io.rrohaill.cleanweatherapp.base.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.rrohaill.cleanweatherapp.usecase.FetchWeatherUseCase
import io.rrohaill.cleanweatherapp.usecase.FetchWeatherUseCaseImpl
import io.rrohaill.cleanweatherapp.usecase.GetWeatherUseCase
import io.rrohaill.cleanweatherapp.usecase.GetWeatherUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    @Singleton
    abstract fun bindFetchWeatherUseCase(impl: FetchWeatherUseCaseImpl): FetchWeatherUseCase

    @Binds
    @Singleton
    abstract fun bindGetWeatherUseCase(impl: GetWeatherUseCaseImpl): GetWeatherUseCase
}