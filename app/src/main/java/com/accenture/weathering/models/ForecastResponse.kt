package com.accenture.weathering.models

import java.io.Serializable

data class ForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<CurrentWeather>,
    val message: Int
): Serializable
