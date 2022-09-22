package com.accenture.weathering

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.accenture.weathering.data.model.Main
import com.accenture.weathering.databinding.FragmentFiveDayBinding
import com.accenture.weathering.databinding.WeatherListItemBinding
import com.accenture.weathering.presentation.adapter.WeatherAdapter
import com.accenture.weathering.presentation.viewmodel.WeatherViewModel
import com.bumptech.glide.load.engine.Resource


class FiveDayFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var  fragmentFiveDayBinding: FragmentFiveDayBinding
    private lateinit var weatherAdapter: WeatherAdapter
    private var lat: Double = 0.0
    private var lon: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_five_day, container, false)
    }

    /** This is to be called immediately after all views has been created
     * to avoid unexpected errors created by partially created views
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentFiveDayBinding = FragmentFiveDayBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        initRecyclerView()
        viewWeatherList()
    }

    private fun viewWeatherList() {
        viewModel.getCurrentWeather(lat, lon)
        viewModel.currentWeather.observe(viewLifecycleOwner) { response ->
            when (response) {
                is com.accenture.weathering.data.util.Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        weatherAdapter.differ.submitList()

                    }
                }
                is com.accenture.weathering.data.util.Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occured: $it", Toast.LENGTH_SHORT).show()
                    }
                }
                is com.accenture.weathering.data.util.Resource.Loading -> {

                }
            }
        }
    }

    private fun initRecyclerView() {
       // weatherAdapter = WeatherAdapter()
        fragmentFiveDayBinding.rvWeather.apply {
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressBar() {
        fragmentFiveDayBinding.progressBar.visibility = View.VISIBLE
        }
    private fun hideProgressBar() {
            fragmentFiveDayBinding.progressBar.visibility = View.INVISIBLE
    }

}