package com.accenture.weathering.domain.repo

import com.accenture.weathering.data.model.CurrentWeather
import com.accenture.weathering.data.util.Resource
import com.accenture.weathering.domain.repo.dataSource.CurrentWeatherRemoteDataSource
import retrofit2.Response

class WeatherRepositoryImpl(
    private val currentWeatherRemoteDataSource: CurrentWeatherRemoteDataSource
):WeatherRepository {
    override suspend fun getCurrentWeather(lat: Double, lon: Double): Resource<CurrentWeather> {
        return responseToResource(currentWeatherRemoteDataSource.getWeather(lat, lon))
    }

    private fun responseToResource(response: Response<CurrentWeather>):Resource<CurrentWeather>{
        if(response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getForecast(): Resource<CurrentWeather> {
        TODO("Not yet implemented")
    }

    override suspend fun getSearchCities(searchQuery: String): Resource<CurrentWeather> {
        TODO("Not yet implemented")
    }
}