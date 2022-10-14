package com.accenture.weathering.data.util

import java.io.IOException

class ApiException(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)
class HttpException(message: String) : IOException(message)
