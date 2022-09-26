package com.accenture.weathering.data.api

import com.accenture.weathering.BuildConfig
import com.accenture.weathering.data.model.CurrentWeather
import com.accenture.weathering.data.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface WeatherAPI {

//https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
//https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}

//    @GET("weather")
//    suspend fun getWeather(
//        @Query("lat") lat: Double,
//        @Query("lon") lon: Double,
//        @Query("appid") appid: String= BuildConfig.APP_ID
//    ):Deferred<CurrentWeather>
//
//    @GET("weather")
//    suspend fun getCityData(
//        @Query("q") cityName: String,
//        @Query("appid") appid: String= BuildConfig.APP_ID
//    ): Deferred<CurrentWeather>
//
//    @GET("forecast")
//    suspend fun getForecast(
//        @Query("lat") lat: Double,
//        @Query("lon") lon: Double,
//        @Query("appid") appid: String= BuildConfig.APP_ID
//    ):Deferred<ForecastResponse>

    // <editor-fold desc="Get Requests">

    @GET("weather")
    suspend fun findCityWeatherData(
        @Query("q") q: String,
        @Query("units") units: String = Constants.WEATHER_UNIT,
        @Query("appid") appid: String = BuildConfig.APP_ID
    ): Response<CurrentWeather>

    // </editor-fold>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): WeatherAPI {

            val WS_SERVER_URL = BuildConfig.BASE_URL
            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(WS_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherAPI::class.java)
        }
    }

}
