package com.accenture.weathering.presentation.di

import com.accenture.weathering.domain.repo.WeatherRepository
import com.accenture.weatheringz.domain.usecase.GetCurrentWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetCurrentWeatherUseCase(weatherRepository: WeatherRepository
    ): GetCurrentWeatherUseCase{
        return GetCurrentWeatherUseCase(weatherRepository)
    }
}