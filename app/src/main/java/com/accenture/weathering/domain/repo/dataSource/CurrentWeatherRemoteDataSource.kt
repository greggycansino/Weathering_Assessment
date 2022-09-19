package com.accenture.weathering.domain.repo.dataSource

import com.accenture.weathering.data.model.CurrentWeather
import retrofit2.Response

interface CurrentWeatherRemoteDataSource {
    suspend fun getWeather(lat: Double, lon: Double):Response<CurrentWeather>


}