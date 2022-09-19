package com.accenture.weathering.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.accenture.weatheringz.domain.usecase.GetCurrentWeatherUseCase

class WeatherViewModelFactory(
    val app: Application,
    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(app, getCurrentWeatherUseCase) as T
    }
}