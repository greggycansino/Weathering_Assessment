package com.accenture.weathering.presentation.adapter

import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.accenture.weathering.data.model.CurrentWeather
import com.accenture.weathering.data.model.WeatherDetail
import com.accenture.weathering.data.util.AppUtil
import com.accenture.weathering.data.util.Constants
import com.accenture.weathering.databinding.WeatherListItemBinding

class WeatherAdapter:RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private val  callback = object : DiffUtil.ItemCallback<WeatherDetail>(){
        override fun areItemsTheSame(oldItem: WeatherDetail, newItem: WeatherDetail): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WeatherDetail, newItem: WeatherDetail): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = WeatherListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weatherDetailList = differ.currentList[position]
        holder.bindItems(weatherDetailList)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class WeatherViewHolder(val binding: WeatherListItemBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bindItems(weatherDetail: WeatherDetail){
            binding.apply {
                val iconCode = weatherDetail.icon?.replace("n", "d")
                AppUtil.setGlideImage(
                    imageViewConditionIcon,
                    Constants.WEATHER_API_IMAGE_ENDPOINT + "${iconCode}@2x.png"
                )
                textViewTemperature.text = weatherDetail.temp.toString()
                textViewDate.text = weatherDetail.dateTime
                textViewCondition.text = weatherDetail.description

            }


        }
    }
}