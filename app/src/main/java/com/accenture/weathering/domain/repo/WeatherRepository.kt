package com.accenture.weathering.domain.repo

import com.accenture.weathering.data.model.CurrentWeather
import com.accenture.weathering.data.util.Resource


interface WeatherRepository {

    suspend fun getCurrentWeather(): Resource<CurrentWeather>
    suspend fun getForecast(): Resource<CurrentWeather>
    suspend fun getSearchCities(searchQuery: String): Resource<CurrentWeather>

    //TODO: view, add, remove persistent list
//    suspend fun saveNews(article: Article)
//    suspend fun deleteNews(article: Article)
//    fun getSavedNews(): Flow<List<Article>>
}