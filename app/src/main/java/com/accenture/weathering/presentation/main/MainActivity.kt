package com.accenture.weathering.presentation.main

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.accenture.weathering.R
import com.accenture.weathering.data.util.*
import com.accenture.weathering.data.util.Constants.DATE_FORMAT
import com.accenture.weathering.data.util.Constants.WEATHER_API_IMAGE_ENDPOINT
import com.accenture.weathering.databinding.FragmentWeatherBinding
import com.accenture.weathering.presentation.adapter.CustomAdapterSearchedCityTemperature
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import java.util.*


class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()
    private lateinit var dataBind: FragmentWeatherBinding
    private val factory: WeatherViewModelFactory by instance()
    private val viewModel: WeatherViewModel by lazy {
        ViewModelProvider(this, factory)[WeatherViewModel::class.java]
    }
    private lateinit var customAdapterSearchedCityTemperature: CustomAdapterSearchedCityTemperature

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    dataBind = DataBindingUtil.setContentView(this@MainActivity, R.layout.fragment_weather)
    setupUI()
    observeAPICall()
}

    private fun setupUI() {
        initializeRecyclerView()
        dataBind.inputFindCityWeather.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.fetchWeatherDetailFromDb((view as EditText).text.toString())
                viewModel.fetchAllWeatherDetailsFromDb()
            }
            false
        }
    }

    private fun initializeRecyclerView() {
        customAdapterSearchedCityTemperature = CustomAdapterSearchedCityTemperature()
        val mLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        dataBind.recyclerViewSearchedCityTemperature.apply {
            layoutManager = mLayoutManager
            itemAnimator = DefaultItemAnimator()
            adapter = customAdapterSearchedCityTemperature
        }
    }

    private fun observeAPICall() {
        viewModel.weatherLiveData.observe(this, EventObserver { resource ->
            when (resource) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    dataBind.apply{
                        textLabelSearchForCity.hide()
                        imageLogo.hide()
                        constraintLayoutShowingTemp.show()
                        inputFindCityWeather.text?.clear()
                    }

                    resource.data.let { weatherDetail ->
                        val iconCode = weatherDetail.icon?.replace("n", "d")
                        AppUtil.setGlideImage(
                            dataBind.imageWeatherSymbol,
                            WEATHER_API_IMAGE_ENDPOINT + "${iconCode}@2x.png"
                        )

                        changeBgAccToTemp(iconCode)

                        dataBind.apply {
                            textTodaysDate.text =
                                AppUtil.getCurrentDateTime(DATE_FORMAT)
                            textTemperature.text = weatherDetail.temp.toString()
                            val cityNameValue = "${
                                weatherDetail.cityName?.replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(
                                        Locale.ROOT
                                    ) else it.toString()
                                }
                            }, ${weatherDetail.countryName}"
                            textCityName.text = cityNameValue

                        }

                    }

                }
                is Resource.Error -> {
                    showToast(resource.message)
                }
            }
        })

        viewModel.weatherDetailListLiveData.observe(this, EventObserver { resource ->
            when (resource) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    if (resource.data.isEmpty()) {
                        dataBind.recyclerViewSearchedCityTemperature.hide()
                    } else {
                        dataBind.recyclerViewSearchedCityTemperature.show()
                        customAdapterSearchedCityTemperature.setData(resource.data)
                    }
                }
                is Resource.Error -> {
                    showToast(resource.message)
                }
            }
        })
    }

    private fun changeBgAccToTemp(iconCode: String?) {
        when (iconCode) {
            "01d", "02d", "03d" -> dataBind.imageWeatherHumanReaction.setImageResource(R.drawable.cf_sunny)
            "04d", "09d", "10d", "11d" -> dataBind.imageWeatherHumanReaction.setImageResource(R.drawable.cf_rain)
            "13d", "50d" -> dataBind.imageWeatherHumanReaction.setImageResource(R.drawable.cf_snow)
        }
    }

}