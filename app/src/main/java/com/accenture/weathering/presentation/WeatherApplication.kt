package com.accenture.weathering.presentation

import android.app.Application
import com.accenture.weathering.data.api.NetworkConnectionInterceptor
import com.accenture.weathering.data.api.WeatherAPI
import com.accenture.weathering.domain.WeatherDatabase
import com.accenture.weathering.domain.repo.WeatherRepository
import com.accenture.weathering.presentation.main.WeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class WeatherApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@WeatherApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { WeatherAPI(instance()) }
        bind() from singleton { WeatherRepository(instance(), instance()) }
        bind() from provider { WeatherViewModelFactory(instance()) }
        bind() from provider { WeatherDatabase(instance()) }
    }


}