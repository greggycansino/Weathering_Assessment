package com.accenture.weathering.repo

import com.accenture.weathering.models.CurrentWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

//https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
//    https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}

        @GET("2.5/weather")
        fun getWeather(
            @Query("lat") lat: Double,
            @Query("lon") lon: Double,
            @Query("units") units: String?,
            @Query("appid") appid: String?
        ): Call<CurrentWeather>

         @GET("2.5/weather")
         fun getCityData(
        @Query("q") cityName: String,
        @Query("appid") appid: String?
         ): Call<CurrentWeather>

}
