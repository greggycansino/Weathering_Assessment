package com.accenture.weathering.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ForecastResponse(
    @SerializedName("city")
    val city: City,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("list")
    val list: List<CurrentWeather>,
    @SerializedName("message")
    val message: Int
)
