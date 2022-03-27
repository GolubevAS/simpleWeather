package com.example.simpleweather



import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleweather.business.model.DailyWeatherModel
import com.example.simpleweather.business.model.HourlyWeatherModel
import com.example.simpleweather.business.model.WeatherDataModel
import com.example.simpleweather.databinding.ActivityMainBinding
import com.example.simpleweather.presenters.MainPresenter
import com.example.simpleweather.view.*
import com.google.android.gms.location.LocationServices
import com.example.simpleweather.view.adapters.MainDailyListAdapter
import com.example.simpleweather.view.adapters.MainHourlyListAdapter
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationRequest
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import java.lang.StringBuilder


class MainActivity : MvpAppCompatActivity(), MainView {

    private val mainPresenter by moxyPresenter { MainPresenter() }

    private val geoService by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }
    private val locationRequest by lazy {
        initLocationRequest()
    }
    private lateinit var mLocations : Location

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()

        binding.mainHourlyList.apply {
            adapter = MainHourlyListAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        binding.mainDailyList.apply {
            adapter = MainDailyListAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }


        val looper = null
        looper?.let { geoService.requestLocationUpdates(locationRequest, geoCallback, it) }

        mainPresenter.enable()

    }


    private fun initView() {
        binding.mainCityTv.text = "Tula"
        binding.mainDateTv.text = "23 march 2022"
        binding.mainWeatherConditionIcon.setImageResource(R.drawable.ic_sun)
        binding.mainWeatherConditionDescription.text = "Ясное небо"
        binding.mainTemp.text = "+25\u00b0"
        binding.mainTempMinTv.text = "5"
        binding.mainTempAverageTv.text = "10"
        binding.mainTempMaxTv.text = "35"
        binding.mainWeatherImage.setImageResource(R.mipmap.palm_sun_3x)
        binding.mainPressureMuTv.text = "1090 Pa"
        binding.mainHumidityMuTv.text = "100%"
        binding.mainWindSpeedMuTv.text = "14 м.с"
        binding.mainSunriseMuTv.text = "07:05"
        binding.mainSunsetMuTv.text = "21:32"
    }


    //--- moxy code---
    override fun displayLocation(data: String) {
       binding.mainCityTv.text = data
    }

    override fun displayCurrentData(data: WeatherDataModel) {
        data.apply {
            binding.mainCityTv.text = "Kiev"
            binding.mainDateTv.text = current.dt.toDataFormatOf(DAY_FULL_MONTH_NAME)
            binding.mainWeatherConditionIcon.setImageResource(current.weather[0].icon.provideIcon())
            binding.mainWeatherConditionDescription.text = current.weather[0].description
            binding.mainTemp.text = StringBuilder().append(current.temp.toDegree()).append("'").toString()
            daily[0].temp.apply {
                binding.mainTempMinTv.text = min.toDegree()
                binding.mainTempAverageTv.text = eve.toDegree()
                binding.mainTempMaxTv.text = max.toDegree()
            }
            binding.mainWeatherImage.setImageResource(current.weather[0].icon.provideIconSun())
            binding.mainPressureMuTv.text = StringBuilder().append(current.pressure.toString()).append(" hPa").toString()
            binding.mainHumidityMuTv.text = StringBuilder().append(current.humidity.toString()).append(" %").toString()
            binding.mainWindSpeedMuTv.text =  StringBuilder().append(current.wind_speed.toString()).append(" m/s").toString()
            binding.mainSunriseMuTv.text = current.sunrise.toDataFormatOf(HOUR_DOUBLE_DOT_MINUTE)
            binding.mainSunsetMuTv.text = current.sunset.toDataFormatOf(HOUR_DOUBLE_DOT_MINUTE)
        }

    }

    override fun displayHourlyData(data: List<HourlyWeatherModel>) {
        (binding.mainHourlyList.adapter as MainHourlyListAdapter).updateData(data)
    }

    override fun displayDailyData(data: List<DailyWeatherModel>) {
        (binding.mainDailyList.adapter as MainDailyListAdapter).updateData(data)
    }

    override fun displayError(error: Throwable) {

    }

    override fun setLoading(flag: Boolean) {

    }


    //--- moxy code---


    //--- location code---

    private fun initLocationRequest() : LocationRequest{
        val request = LocationRequest.create()
        return request.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    private val geoCallback = object : LocationCallback() {
        override fun onLocationResult (geo : LocationResult) {
            for (location in geo.locations) {
                mLocations = location
                mainPresenter.refresh(location.latitude.toString(), mLocations.longitude.toString())
            }
        }
    }


    //--- location code---









}