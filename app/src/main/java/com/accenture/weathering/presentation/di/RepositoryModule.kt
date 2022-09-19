package com.accenture.weathering.presentation.di

import com.accenture.weathering.domain.repo.WeatherRepository
import com.accenture.weathering.domain.repo.WeatherRepositoryImpl
import com.accenture.weathering.domain.repo.dataSource.CurrentWeatherRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(currentWeatherRemoteDataSource: CurrentWeatherRemoteDataSource
    ):WeatherRepository{
        return WeatherRepositoryImpl(currentWeatherRemoteDataSource)
    }
}