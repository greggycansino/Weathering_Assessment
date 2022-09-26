package com.accenture.weathering.domain.repo

import com.accenture.weathering.data.api.SafeApiRequest
import com.accenture.weathering.data.api.WeatherAPI
import com.accenture.weathering.data.model.CurrentWeather
import com.accenture.weathering.data.model.WeatherDetail
import com.accenture.weathering.domain.WeatherDatabase

class WeatherRepository(
    private val api: WeatherAPI,
    private val db: WeatherDatabase
) : SafeApiRequest() {

    suspend fun findCityWeather(cityName: String): CurrentWeather = apiRequest {
        api.findCityWeatherData(cityName)
    }

    suspend fun addWeather(weatherDetail: WeatherDetail) {
        db.getWeatherDao().addWeather(weatherDetail)
    }

    suspend fun fetchWeatherDetail(cityName: String): WeatherDetail? =
        db.getWeatherDao().fetchWeatherByCity(cityName)

    suspend fun fetchAllWeatherDetails(): List<WeatherDetail> =
        db.getWeatherDao().fetchAllWeatherDetails()
}