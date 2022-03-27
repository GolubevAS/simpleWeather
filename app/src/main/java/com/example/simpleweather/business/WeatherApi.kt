package com.example.simpleweather.business


import com.example.simpleweather.business.model.WeatherDataModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    @GET("data/2.5/onecall?")
    fun getWeatherForecast(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        @Query("exclude") exclude : String = "minutely,alerts",
        @Query("appid") appid : String = "2464e2e4321ac072c7a8011bf47da68e",
        @Query("lang")lang : String = "en"
    ) : Observable<WeatherDataModel>

}