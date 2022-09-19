package com.accenture.weathering.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.accenture.weathering.data.model.CurrentWeather
import com.accenture.weathering.data.util.Resource
import com.accenture.weatheringz.domain.usecase.GetCurrentWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(
    val app: Application,
    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
): AndroidViewModel(app) {
    val currentWeather: MutableLiveData<Resource<CurrentWeather>> = MutableLiveData()

    fun getCurrentWeather(lat: Double, lon: Double) = viewModelScope.launch(Dispatchers.IO) {
        currentWeather.postValue(Resource.Loading())
        try {
            if (isInternetAvailable(app)) {

                val apiResult = getCurrentWeatherUseCase.execute(lat, lon)
                currentWeather.postValue(apiResult)
            } else {
                currentWeather.postValue(Resource.Error("Internet is not available"))
            }
        } catch (e: Exception){
            currentWeather.postValue((Resource.Error(e.message.toString())))
    }
    }

    @Suppress("DEPRECATION")
    //checks if internet is available considering different possible mediums and return true if internet is available
    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }


}