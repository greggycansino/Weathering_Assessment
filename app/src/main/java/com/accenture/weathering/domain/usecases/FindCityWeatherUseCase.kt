package com.accenture.weathering.domain.usecases

import com.accenture.weathering.domain.repo.WeatherRepository


class FindCityWeatherUseCase(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(cityName: String) =
        repository.findCityWeather(cityName)

}