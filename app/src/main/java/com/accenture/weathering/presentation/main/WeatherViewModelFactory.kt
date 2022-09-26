package com.accenture.weathering.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.accenture.weathering.domain.repo.WeatherRepository

@Suppress("UNCHECKED_CAST")
class WeatherViewModelFactory(
   private val repository: WeatherRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(repository) as T
    }
}