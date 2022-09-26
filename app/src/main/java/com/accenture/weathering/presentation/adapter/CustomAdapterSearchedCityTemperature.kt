package com.accenture.weathering.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.accenture.weathering.R
import com.accenture.weathering.data.model.WeatherDetail
import com.accenture.weathering.data.util.AppUtil
import com.accenture.weathering.data.util.Constants
import com.accenture.weathering.databinding.ItemSearchedCityBinding
import java.util.*
import kotlin.collections.ArrayList


class CustomAdapterSearchedCityTemperature :
    RecyclerView.Adapter<CustomAdapterSearchedCityTemperature.ViewHolder>() {

    private val weatherDetailList = ArrayList<WeatherDetail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemSearchedCityBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_searched_city,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(weatherDetailList[position])
    }

    override fun getItemCount(): Int = weatherDetailList.size

    fun setData(
        newWeatherDetail: List<WeatherDetail>
    ) {
        weatherDetailList.clear()
        weatherDetailList.addAll(newWeatherDetail)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemSearchedCityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindItems(weatherDetail: WeatherDetail) {
            binding.apply {
                val iconCode = weatherDetail.icon?.replace("n", "d")
                AppUtil.setGlideImage(
                    imageWeatherSymbol,
                    Constants.WEATHER_API_IMAGE_ENDPOINT + "${iconCode}@4x.png"
                )
                textCityName.text =
                    "${weatherDetail.cityName?.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    }}, ${weatherDetail.countryName}"
                textTemperature.text = weatherDetail.temp.toString()
                textDateTime.text = weatherDetail.dateTime
            }
        }
    }
}
