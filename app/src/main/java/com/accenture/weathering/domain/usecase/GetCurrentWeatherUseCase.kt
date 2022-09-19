package com.accenture.weatheringz.domain.usecase

import com.accenture.weathering.data.model.CurrentWeather
import com.accenture.weathering.data.util.Resource
import com.accenture.weathering.domain.repo.WeatherRepository

class GetCurrentWeatherUseCase(private val currentWeatherRepository: WeatherRepository) {

    suspend fun execute(lat: Double, lon: Double): Resource<CurrentWeather> {
        return currentWeatherRepository.getCurrentWeather(lat, lon)
    }
}