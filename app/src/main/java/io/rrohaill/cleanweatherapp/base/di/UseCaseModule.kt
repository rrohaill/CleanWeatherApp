package io.rrohaill.cleanweatherapp.base.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.rrohaill.cleanweatherapp.domain.usecase.FetchWeatherUseCase
import io.rrohaill.cleanweatherapp.domain.usecase.FetchWeatherUseCaseImpl
import io.rrohaill.cleanweatherapp.domain.usecase.GetWeatherUseCase
import io.rrohaill.cleanweatherapp.domain.usecase.GetWeatherUseCaseImpl
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