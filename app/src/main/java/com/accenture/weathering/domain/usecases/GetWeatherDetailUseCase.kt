package com.accenture.weathering.domain.usecases

import com.accenture.weathering.domain.repo.WeatherRepository


class GetWeatherDetailUseCase constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(cityName: String) =
        repository.fetchWeatherDetail(cityName)

}