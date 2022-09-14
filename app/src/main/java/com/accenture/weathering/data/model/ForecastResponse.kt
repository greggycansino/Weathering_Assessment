package com.accenture.weathering.data.model

import java.io.Serializable

data class ForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<CurrentWeather>,
    val message: Int
): Serializable
