package com.accenture.weathering.data.util

// a generic class that contains data and status about loading this data
//keeps track of status returned from API
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
){
   class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String?, data: T? = null) : Resource<T>(data, message)
}
