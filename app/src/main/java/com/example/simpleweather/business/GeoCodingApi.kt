package com.example.simpleweather.business

import com.example.simpleweather.business.model.GeoCodeModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoCodingApi {

    @GET("geo/1.0/reverse?")
    fun getCityByCoord(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        @Query("limit") limit : String = "10",
        @Query("appid") id : String = "057b5be1513450651f0f7e2275c89531"
    ) : Observable<List<GeoCodeModel>>
}