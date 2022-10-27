package com.accenture.weathering.domain.usecases

import com.accenture.weathering.data.model.WeatherDetail
import com.accenture.weathering.domain.repo.WeatherRepository


class AddWeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(weatherDetail: WeatherDetail) =
        repository.addWeather(weatherDetail)

}