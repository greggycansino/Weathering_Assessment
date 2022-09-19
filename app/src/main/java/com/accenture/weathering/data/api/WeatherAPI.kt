package com.accenture.weathering.data.api

import com.accenture.weathering.BuildConfig
import com.accenture.weathering.data.model.CurrentWeather
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

//https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
//https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}

        @GET("2.5/weather")
        suspend fun getWeather(
            @Query("lat") lat: Double,
            @Query("lon") lon: Double,
            @Query("appid") appid: String= BuildConfig.APP_ID
        ):Response<CurrentWeather>

         @GET("2.5/weather")
         suspend fun getCityData(
        @Query("q") cityName: String,
        @Query("appid") appid: String= BuildConfig.APP_ID
         ): Response<CurrentWeather>

}
