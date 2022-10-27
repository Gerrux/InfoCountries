package com.example.infoaboutcountries

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface RestCountriesService {
    @GET("name/{name}?access_key=ca9741d42b55c1848501e2a5c41540ee")
    fun getCountryByName(@Path("cityName") cityName: String): List<Country>
}

var retrofit = Retrofit.Builder()
    .baseUrl("https://api.countrylayer.com/v2/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var restCountriesService = retrofit.create(RestCountriesService::class.java)