package com.accenture.weathering.domain.usecases

import com.accenture.weathering.domain.repo.WeatherRepository

class GetWeatherDetailListUseCase(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke() =
        repository.fetchAllWeatherDetails()

}
