package com.accenture.weathering.models

import java.io.Serializable

data class Main(
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
): Serializable
