package io.rrohaill.cleanweatherapp.base.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.rrohaill.cleanweatherapp.base.network.ApiFactory
import io.rrohaill.cleanweatherapp.base.network.WeatherApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApi(@ApplicationContext appContext: Context): WeatherApi =
        ApiFactory.getApi(context = appContext)
}