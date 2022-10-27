package com.accenture.weathering.domain.usecases

class GeneralUseCases(
    val addWeather: AddWeatherUseCase,
    val findCityWeather: FindCityWeatherUseCase,
    val getWeatherDetail: GetWeatherDetailUseCase,
    val getWeatherDetailList: GetWeatherDetailListUseCase
)
