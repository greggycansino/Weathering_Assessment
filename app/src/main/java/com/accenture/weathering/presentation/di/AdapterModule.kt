package com.accenture.weathering.presentation.di

import com.accenture.weathering.presentation.adapter.WeatherAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun provideWeatherAdapter(): WeatherAdapter{
        return WeatherAdapter()
    }
}