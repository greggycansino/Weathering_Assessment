package com.accenture.weathering.data.util

// a generic class that contains data and status about loading this data
//keeps track of status returned from API
sealed class Resource<T>{
    class Loading<T> : Resource<T>()

data class Success<T>(val data: T) : Resource<T>()

data class Error<T>(val message: String) : Resource<T>()

companion object {
    fun <T> loading() = Loading<T>()
    fun <T> success(data: T) = Success(data)
    fun <T> error(message: String) = Error<T>(message)
}
}
