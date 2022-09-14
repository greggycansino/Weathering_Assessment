package com.accenture.weatheringz.domain.usecase

import com.accenture.weathering.data.model.CurrentWeather
import com.accenture.weathering.data.util.Resource
import com.accenture.weathering.domain.repo.WeatherRepository

class GetSearchCitiesUseCase(private val currentWeatherRepository: WeatherRepository) {

        suspend fun execute(searchQuery:String): Resource<CurrentWeather> {
            return currentWeatherRepository.getSearchCities(searchQuery)
        }
}