package com.accenture.weathering.presentation.di

import com.accenture.weathering.data.api.WeatherAPI
import com.accenture.weathering.domain.repo.dataSource.CurrentWeatherRemoteDataSource
import com.accenture.weathering.domain.repo.dataSourceImpl.CurrentWeatherRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    //to construct a WeatherRemoteDataSourceImpl inside this function we need to provide the WeatherAPI dependency as a parameter
    fun provideCurrentWeatherRemoteDataSource(weatherAPI: WeatherAPI
    ):CurrentWeatherRemoteDataSource{
        return CurrentWeatherRemoteDataSourceImpl(weatherAPI)
    }
}