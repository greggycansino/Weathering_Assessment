package com.accenture.weathering.domain.repo.dataSourceImpl

import com.accenture.weathering.data.api.WeatherAPI
import com.accenture.weathering.data.model.CurrentWeather
import com.accenture.weathering.domain.repo.dataSource.CurrentWeatherRemoteDataSource
import retrofit2.Response

class CurrentWeatherRemoteDataSourceImpl(
    private val weatherAPI: WeatherAPI
    ):CurrentWeatherRemoteDataSource {
    override suspend fun getWeather(lat: Double, lon: Double): Response<CurrentWeather> {
        return weatherAPI.getWeather(lat,lon)
    }

}