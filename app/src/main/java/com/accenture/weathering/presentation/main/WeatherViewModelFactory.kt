package com.accenture.weathering.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.accenture.weathering.domain.usecases.GeneralUseCases

@Suppress("UNCHECKED_CAST")
class WeatherViewModelFactory(
    private val generalUseCases: GeneralUseCases
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(generalUseCases) as T
    }
}