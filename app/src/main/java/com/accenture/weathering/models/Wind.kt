package com.accenture.weathering.models

import java.io.Serializable

data class Wind(
    val deg: Int,
    val speed: Double
): Serializable
