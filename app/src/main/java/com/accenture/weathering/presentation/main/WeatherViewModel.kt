package com.accenture.weathering.presentation.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.accenture.weathering.data.model.CurrentWeather
import com.accenture.weathering.data.model.WeatherDetail
import com.accenture.weathering.data.util.*
import com.accenture.weathering.domain.usecases.GeneralUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class WeatherViewModel(
    private val generalUseCases: GeneralUseCases
) :
    ViewModel() {

    private val _weatherLiveData =
        MutableLiveData<Event<Resource<WeatherDetail>>>()
    val weatherLiveData: LiveData<Event<Resource<WeatherDetail>>>
        get() = _weatherLiveData

    private val _weatherDetailListLiveData =
        MutableLiveData<Event<Resource<List<WeatherDetail>>>>()
    val weatherDetailListLiveData: LiveData<Event<Resource<List<WeatherDetail>>>>
        get() = _weatherDetailListLiveData

    private lateinit var currentWeather: CurrentWeather

    private fun findCityWeather(cityName: String) {
        _weatherLiveData.postValue(Event(Resource.loading()))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                currentWeather = generalUseCases.findCityWeather(cityName)
                addWeatherDetailIntoDb(currentWeather)
                withContext(Dispatchers.Main) {
                    val weatherDetail = WeatherDetail()
                    weatherDetail.icon = currentWeather.weather.first().icon
                    weatherDetail.cityName = currentWeather.name
                    weatherDetail.countryName = currentWeather.sys.country
                    weatherDetail.temp = currentWeather.main.temp
                    _weatherLiveData.postValue(
                        Event(
                            Resource.success(
                                weatherDetail
                            )
                        )
                    )
                }
            } catch (e: ApiException) {
                withContext(Dispatchers.Main) {
                    _weatherLiveData.postValue(Event(Resource.error(e.message ?: "No API detected")))
                }
            } catch (e: NoInternetException) {
                withContext(Dispatchers.Main) {
                    _weatherLiveData.postValue(Event(Resource.error(e.message ?: "No Internet")))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _weatherLiveData.postValue(
                        Event(
                            Resource.error(
                                e.message ?: "No value displayed."
                            )
                        )
                    )
                }
            }
        }
    }

    private suspend fun addWeatherDetailIntoDb(currentWeather: CurrentWeather) {
        val weatherDetail = WeatherDetail()
        weatherDetail.id = currentWeather.id
        weatherDetail.icon = currentWeather.weather.first().icon
        weatherDetail.cityName = currentWeather.name.lowercase(Locale.ROOT)
        weatherDetail.countryName = currentWeather.sys.country
        weatherDetail.temp = currentWeather.main.temp
        weatherDetail.dateTime = AppUtil.getCurrentDateTime(Constants.DATE_FORMAT_1)
        generalUseCases.addWeather(weatherDetail)
    }

    fun fetchWeatherDetailFromDb(cityName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val weatherDetail = generalUseCases.getWeatherDetail(cityName.lowercase(Locale.ROOT))
            withContext(Dispatchers.Main) {
                if (weatherDetail != null) {
                    // Return true if current date and time is greater than the saved date and time of weather searched
                    if (AppUtil.isTimeExpired(weatherDetail.dateTime)) {
                        findCityWeather(cityName)
                    } else {
                        _weatherLiveData.postValue(
                            Event(Resource.success(weatherDetail))
                        )
                    }

                } else {
                    findCityWeather(cityName)
                }

            }
        }
    }

    fun fetchAllWeatherDetailsFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val weatherDetailList = generalUseCases.getWeatherDetailList()
            withContext(Dispatchers.Main) {
                _weatherDetailListLiveData.postValue(
                    Event(
                        Resource.success(weatherDetailList)
                    )
                )
            }
        }
    }
}