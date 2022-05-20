package com.example.finalproject

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryAPI {

    @GET("/countries")
    suspend fun getCountries(): Response<List<Country>>
}