package com.accenture.weathering.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Clouds(
    @SerializedName("all")
    val all: Int
)
