package com.accenture.weathering.data.model

import android.accounts.AuthenticatorDescription
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.accenture.weathering.data.model.WeatherDetail.Companion.TABLE_NAME

/**
 * Data class for Database entity and Serialization.
 */

@Entity(tableName = TABLE_NAME)
data class WeatherDetail(
    @PrimaryKey
    var id: Int? = 0,
    var temp: Double? = null,
    var icon: String? = null,
    var cityName: String? = null,
    var countryName: String? = null,
    var dateTime: String? = null,
    var main: String? = null,
    var description: String? = null,
    var humidity: String? = null,
    var wind_speed: Double? = null,
    var pressure: Int? = null,
    var sunrise: Int? = null,
    var sunset: Int? = null

) {
    companion object {
        const val TABLE_NAME = "weather_detail"
    }
}