package com.accenture.weathering

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.accenture.weathering.BuildConfig.APP_ID
import com.accenture.weathering.databinding.ActivityMainBinding
import com.accenture.weathering.data.model.CurrentWeather
import com.accenture.weathering.data.api.WeatherAPIService
import com.accenture.weathering.data.util.Constants
import com.accenture.weathering.data.util.Constants.METRIC_UNIT
import com.bumptech.glide.Glide
import com.google.android.gms.location.*
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

        private lateinit var binding:ActivityMainBinding
//
//
//    private lateinit var mFusedLocationClient: FusedLocationProviderClient
//
//    private var mLatitude: Double = 0.0
//    private var mLongitude: Double = 0.0
//
//    private lateinit var mSharedPreferences: SharedPreferences
//
//
//    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    val navHostFragment = supportFragmentManager.findFragmentById(R.id.weatherFragment) as NavHostFragment
    val navController = navHostFragment.navController
    binding.bnvWeather.setupWithNavController(navController)
//
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//
//        mSharedPreferences = getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE) //only visible in this application only
//
//        setupUI()
//
//        getCurrentLocation()
//
//
//        binding.swipeRefreshLayout.setOnRefreshListener {
//            binding.apply {
//                llData.visibility = View.GONE
//                tvError.visibility = View.GONE
//                pbLoading.visibility = View.GONE
//            }
//
//            requestLocationData()
//            binding.apply {
//                llData.visibility = View.VISIBLE
//                tvError.visibility = View.VISIBLE
//                pbLoading.visibility = View.VISIBLE
//            }
//
//            swipe_refresh_layout.isRefreshing = false
//        }
//
//        binding.imgSearchCity.setOnClickListener {
//            val cityName = binding.edtCityName.text.toString()
//            mSharedPreferences.edit().putString("cityName", cityName).apply()
//            requestLocationData()
//            Log.i("Main Activity", "onCreate: $cityName")
//        }
//
//        binding.nightModeSwitch.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            } else{
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            }
//
//        }

    }

//
//    private fun getCurrentLocation(){
//        if (isLocationEnabled()) {
//            Dexter.withActivity(this)
//                .withPermissions(
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_COARSE_LOCATION
//                )
//                .withListener(object : MultiplePermissionsListener {
//                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
//                        if (report!!.areAllPermissionsGranted()) {
//                            requestLocationData()
//                        }
//
//                        if (report.isAnyPermissionPermanentlyDenied) {
//                            Toast.makeText(
//                                this@MainActivity,
//                                "You have denied location permission. Please allow it is mandatory.",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//
//                    override fun onPermissionRationaleShouldBeShown(
//                        permissions: MutableList<PermissionRequest>?,
//                        token: PermissionToken?
//                    ) {
//                        showRationalDialogForPermissions()
//                    }
//                }).onSameThread()
//                .check()
//
//        }
//        else {
//            Toast.makeText(
//                this,
//                "Location provider is turned off. Please turn it on.",
//                Toast.LENGTH_SHORT
//            ).show()
//
//            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//            startActivity(intent)
//        }
//
//    }
//
//
//    private fun isLocationEnabled(): Boolean {
//
//        val locationManager: LocationManager =
//            getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
//            LocationManager.NETWORK_PROVIDER
//        )
//    }
//
//
//    private fun showRationalDialogForPermissions() {
//        AlertDialog.Builder(this)
//            .setMessage("Location Permission is required to use the app. Enable it under Application Settings.")
//            .setPositiveButton(
//                "GO TO SETTINGS"
//            ) { _, _ ->
//                try {
//                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//                    val uri = Uri.fromParts("package", packageName, null)
//                    intent.data = uri
//                    startActivity(intent)
//                } catch (e: ActivityNotFoundException) {
//                    e.printStackTrace()
//                }
//            }
//            .setNegativeButton("Cancel") { dialog, _ ->
//                dialog.dismiss()
//            }.show()
//    }
//
//
//    @SuppressLint("MissingPermission")
//    private fun requestLocationData() {
//
//        val mLocationRequest = LocationRequest()
//        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//        mFusedLocationClient.requestLocationUpdates(
//            mLocationRequest, mLocationCallback,
//            Looper.myLooper()
//        )
//    }
//
//
//    private val mLocationCallback = object : LocationCallback() {
//        override fun onLocationResult(locationResult: LocationResult) {
//
//            val mLastLocation: Location = locationResult.lastLocation!!
//            mLatitude = mLastLocation.latitude
//            Log.e("Current Latitude", "$mLatitude")
//            mLongitude = mLastLocation.longitude
//            Log.e("Current Longitude", "$mLongitude")
//
//            getLocationWeatherDetails()
//        }
//    }
//
//
//    private fun getLocationWeatherDetails() {
//
//        if (isNetworkAvailable(this@MainActivity)) {
//
//            WeatherAPIService()
//
//            val listCall: Call<CurrentWeather> = WeatherAPIService().service.getWeather(
//                mLatitude, mLongitude, METRIC_UNIT, APP_ID)
//
//            listCall.enqueue(object : Callback<CurrentWeather> {
//                @RequiresApi(Build.VERSION_CODES.N)
//                override fun onResponse(
//                    call: Call<CurrentWeather>,
//                    response: Response<CurrentWeather>
//                ) {
//                    if (response.isSuccessful) {
//
//                        val weatherList: CurrentWeather? = response.body()
//                        Log.i("Response Result", "$weatherList")
//
//
//                        val currentWeatherJsonString = Gson().toJson(weatherList)
//                        val editor = mSharedPreferences.edit()
//                        editor.putString(Constants.CURRENT_WEATHER_DATA, currentWeatherJsonString) // limited to async data types (cannot pass whole object)
//                        editor.apply()
//
//                        setupUI()
//
//                    } else {
//                        when (response.code()) {
//                            400 -> {
//                                Log.e("Error 400", "Bad Request")
//                            }
//                            404 -> {
//                                Log.e("Error 404", "Not Found")
//                            }
//                            else -> {
//                                Log.e("Error", "Generic Error")
//                            }
//                        }
//                    }                }
//
//                override fun onFailure(call: Call<CurrentWeather>, t: Throwable) {
//                    Log.e("Error", t.message.toString())
//                }
//            })
//        } else {
//            Toast.makeText(
//                this@MainActivity,
//                "No internet connection available.",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }
//
//    @SuppressLint("SetTextI18n")
//    @RequiresApi(Build.VERSION_CODES.N)
//    private fun setupUI() {
//
//        val currentWeatherJsonString =
//            mSharedPreferences.getString(Constants.CURRENT_WEATHER_DATA, "")
//
//
//        if (!currentWeatherJsonString.isNullOrEmpty()) {
//
//            val weatherList =
//                Gson().fromJson(currentWeatherJsonString, CurrentWeather::class.java)
//
//            for (z in weatherList.weather.indices) {
//                Log.i("WEATHER NAME", weatherList.weather[z].main)
//                binding.apply {
//                    ll_data.visibility = View.VISIBLE
//                    tvMain.text = weatherList.weather[z].main
//                    tvMainDescription.text = weatherList.weather[z].description
//                    tvDegree.text =
//                        weatherList.main.temp.toString() + getUnit(application.resources.configuration.locales.toString())
//                    tvHumidity.text = weatherList.main.humidity.toString() + " %"
//                        tvWindSpeed.text = weatherList.wind.speed.toString()+ " mpH"
//                    tvCityName.text = weatherList.name
//                    tvCityCode.text = weatherList.sys.country
//                    tvSunrise.text = unixTime(weatherList.sys.sunrise)
//                    tvSunset.text = unixTime(weatherList.sys.sunset)
//                }
//
//                Glide.with(this)
//                    .load("https://openweathermap.org/img/wn/" + weatherList.weather[z].icon + "@2x.png")
//                    .into(img_weather_pictures)
//
//            }
//        }
//    }
//
//    private fun getUnit(value: String): String? {
//        Log.i("unit", value)
//        var value = "°C"
//        if ("US" == value || "LR" == value || "MM" == value) {
//            value = "°F"
//        }
//        return value
//    }
//
//
//    private fun unixTime(timex: Int): String? {
//        val date = Date(timex * 1000L)
//        @SuppressLint("SimpleDateFormat") val sdf =
//            SimpleDateFormat("HH:mm")
//        sdf.timeZone = TimeZone.getDefault()
//        return sdf.format(date)
//    }

}