package com.accenture.weathering.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset

class WeatherAPITest {
    private lateinit var service: WeatherAPI
    private lateinit var server: MockWebServer

    @Before
    fun setUp(){
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(" "))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }

    private fun enqueueMockResponse(
        fileName: String
    ){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charset.defaultCharset()))
        server.enqueue(mockResponse)
    }
    @Test
    fun getWeather_sendRequest_receivedExpected(){
        //runBlocking - couroutine builder used for testing: Runs a new coroutine and blocks the current thread until completion
        runBlocking {
          enqueueMockResponse("weatherresponse.json")  //starts mockwebserver and prepare fake response using local json file
            val responsebody = service.getWeather(14.5833,121.0).body()
            val request = server.takeRequest()
            assertThat(responsebody).isNotNull()
            assertThat(request.path).isEqualTo("/2.5/weather?lat=14.5833&lon=121.0&appid=3e85b3f91273c953666f29221f1586a5")
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}