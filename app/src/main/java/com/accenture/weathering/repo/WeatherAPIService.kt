package com.accenture.weathering.repo

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.accenture.weathering.models.CurrentWeather
import com.accenture.weathering.util.Constants
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherAPIService {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) //transforms data into the right format
        .build()

    val service: WeatherAPI =
        retrofit.create(WeatherAPI::class.java) //prepare service to be able to call it

}