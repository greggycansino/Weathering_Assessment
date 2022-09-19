package com.accenture.weathering.presentation.di

import android.app.Application
import com.accenture.weathering.domain.repo.WeatherRepository
import com.accenture.weathering.presentation.viewmodel.WeatherViewModelFactory
import com.accenture.weatheringz.domain.usecase.GetCurrentWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideWeatherViewModelFactory(
        application: Application,
        getCurrentWeatherUseCase: GetCurrentWeatherUseCase
        ): WeatherViewModelFactory{

        return WeatherViewModelFactory( application, getCurrentWeatherUseCase)
    }
}