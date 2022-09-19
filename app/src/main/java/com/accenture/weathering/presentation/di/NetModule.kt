package com.accenture.weathering.presentation.di

import com.accenture.weathering.BuildConfig
import com.accenture.weathering.data.api.WeatherAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //annotate to tell which Android class each module will be used or installed in
class NetModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }
    @Singleton
    @Provides
    //provider function to return instance of WeatherAPI
    fun providesWeatherAPI(retrofit: Retrofit): WeatherAPI{
        return retrofit.create(WeatherAPI::class.java)
    }

}