package com.example.retrofitforecaster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitforecaster.databinding.ActivityMainBinding
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    private val weatherAdapter = WeatherAdapter()
    private lateinit var binding: ActivityMainBinding
    private var weatherAPI = WeatherAPI.createAPI()
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.plant(Timber.DebugTree())
        recycleViewInit()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.weatherList.observe(this){
            weatherAdapter.submitList(it)
        }

    }
    private fun recycleViewInit() {
        binding.rvWeather.adapter = weatherAdapter
        binding.rvWeather.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
    }
}