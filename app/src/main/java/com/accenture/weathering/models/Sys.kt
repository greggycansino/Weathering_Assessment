package com.accenture.weathering.models

import java.io.Serializable

data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
): Serializable
