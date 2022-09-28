package com.accenture.weathering.data.api

import com.accenture.weathering.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
//https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}

class WeatherAPIService {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) //transforms data into the right format
        .build()

    val service: WeatherAPI =
        retrofit.create(WeatherAPI::class.java) //prepare service to be able to call it

}