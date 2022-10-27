package com.example.infoaboutcountries

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface RestCountriesService {
    @GET("name/{cityName}")
    suspend fun getCountryByName(@Path("cityName") cityName: String): List<Country>
}

var retrofit = Retrofit.Builder()
    .baseUrl("https://countriesinfo21.herokuapp.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var restCountriesService = retrofit.create(RestCountriesService::class.java)