package com.accenture.weathering.data.util

import android.annotation.SuppressLint
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

object AppUtil {

    fun setGlideImage(image: ImageView, url: String) {
        Glide.with(image).load(url).into(image)
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDateTime(dateFormat: String): String =
        SimpleDateFormat(dateFormat).format(Date())

    //    private fun unixTime(timex: Int): String? {
//        val date = Date(timex * 1000L)
//        @SuppressLint("SimpleDateFormat") val sdf =
//            SimpleDateFormat("HH:mm")
//        sdf.timeZone = TimeZone.getDefault()
//        return sdf.format(date)

    @SuppressLint("SimpleDateFormat")
    fun isTimeExpired(dateTimeSavedWeather: String?): Boolean {
        dateTimeSavedWeather?.let {
            val currentDateTime = Date()
            val savedWeatherDateTime =
                SimpleDateFormat(Constants.DATE_FORMAT_1).parse(it)
            val diff: Long = currentDateTime.time - savedWeatherDateTime.time
            val seconds = diff / 1000
            val minutes = seconds / 60
            if (minutes > 10)
                return true
        }
        return false
    }
}