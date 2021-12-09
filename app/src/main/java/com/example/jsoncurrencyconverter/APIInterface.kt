package com.example.jsoncurrencyconverter

import retrofit2.Call
//import retrofit2.http.*
import retrofit2.http.Headers
import retrofit2.http.GET

interface APIInterface {

    @GET("eur.json")

    fun getCurrenciesValues(): Call<APIData>
}