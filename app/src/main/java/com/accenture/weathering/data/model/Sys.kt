package com.accenture.weathering.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Sys(
    @SerializedName("country")
    val country: String,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int
)
